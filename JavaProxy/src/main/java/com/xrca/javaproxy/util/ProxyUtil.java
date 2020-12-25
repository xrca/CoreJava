package com.xrca.javaproxy.util;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author xrca
 * @description 动态代理
 * @date 2020-12-25 13:54
 **/
public class ProxyUtil {

    public static Object newProxyInstance(Object target) {
        String content = "";
        Class targetInfo = target.getClass().getInterfaces()[0];
        String packageContent = "package com.xrca.javaproxy.proxy;";
        String importContent = "import " + targetInfo.getName() + ";";
        String firstContent = "public class $Proxy implements " + targetInfo.getSimpleName() + " {";
        String fieldContent = "private " + targetInfo.getSimpleName() + " target;";
        String constructorContent = "public $Proxy(" + targetInfo.getSimpleName() + " target) {"
                + "this.target = target;}";


        Method[] methods = targetInfo.getDeclaredMethods();
        String methodsContent = "";
        for (Method method : methods) {
            String returnTypeName = method.getReturnType().getSimpleName();
            String methodName = method.getName();
            Class[] parameterTypes = method.getParameterTypes();
            String argsContent = "";
            String paramsContent = "";
            int i = 0;
            for (Class parameterType : parameterTypes) {
                String simpleName = parameterType.getSimpleName();
                argsContent += simpleName + " p" + i + ", ";
                paramsContent += "p" + i + ", ";
                i++;
            }
            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
            }
            methodsContent += "public " + returnTypeName + " " + methodName + " (" + argsContent + ") {" +
                    "System.out.println(\"log proxy...\");" + "target." + methodName + "(" + paramsContent + ");}";
        }
        //packageContent +
        content +=  importContent + firstContent + fieldContent + constructorContent
                + methodsContent + "}";

        File file = new File("C:/Users/fishs/workspace/IdeaProjects/CoreJava/$Proxy.java");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable units = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager,
                    null, null, null, units);
            task.call();
            manager.close();

            URL[] urls = new URL[]{new URL("file:C:\\\\Users\\fishs\\workspace\\IdeaProjects\\CoreJava\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("$Proxy");
            Constructor constructor = clazz.getConstructor(targetInfo);
            Object o = constructor.newInstance(target);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.xrca.java8;

import lombok.Data;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Jijin {
    public static void main(String[] args) {
        // 最大净值
        BigDecimal highVal = new BigDecimal(1);
        // TODO 最小净值 即最大回撤，手动输入
        BigDecimal lowValue = new BigDecimal(0.78);
        // TODO 定投金额 手动输入
        BigDecimal money = new BigDecimal(10000);
        // TODO 实际净值 手动输入
        BigDecimal actualVal = new BigDecimal(3.5287);

        BigDecimal times = highVal.subtract(lowValue).divide(new BigDecimal(0.02), MathContext.DECIMAL64);
        // 定投次数，可手动设置，亦可以多次划分后，获取精细定投计划
        int dtTimes = times.intValue();

        DTPlan(highVal, lowValue, money, dtTimes, actualVal, null);

        //System.out.println(calcRate(20, new BigDecimal(0.01)));
    }

    /**
     * 定投计划
     * @param highVal   最高净值
     * @param lowValue  最低净值
     * @param planMoney 计划投资金额
     * @param dtTimes   计划投资次数
     * @param actualVal 实际净值
     * @param rate0     第一次定投金额，不设置默认为总金额的百分之一
     */
    public static void DTPlan(BigDecimal highVal, BigDecimal lowValue, BigDecimal planMoney, int dtTimes, BigDecimal actualVal, BigDecimal rate0) {
        // 最大回撤
        BigDecimal Hm = highVal.subtract(lowValue);

        // 第一份定投金额固定位总金额的百分之一
        if (rate0 == null) {
            rate0 = new BigDecimal(0.01);
        }

        // 之后每次定投增长
        BigDecimal rate = calcRate(dtTimes, rate0);

        BigDecimal valRate = Hm.divide(new BigDecimal(dtTimes), MathContext.DECIMAL64);

        BigDecimal lastDTMoney = null;
        BigDecimal totalDTMoney = BigDecimal.ZERO;
        // 计算最终亏损
        BigDecimal tmp = BigDecimal.ZERO;
        for (int i = 1; i <= dtTimes; i++) {
            // 当前轮次定投金额
            BigDecimal DT = null;
            if (lastDTMoney == null) {
                DT = planMoney.multiply(rate0);
            } else {
                DT = lastDTMoney.multiply(rate);
            }
            lastDTMoney = DT;
            // 当前轮次基金净值
            BigDecimal X = highVal.subtract(highVal.multiply(new BigDecimal(i)).multiply(valRate, MathContext.DECIMAL64));
            BigDecimal val = X.multiply(actualVal, MathContext.DECIMAL64);
            System.out.println("第" + i + "次定投：基金净值：" + val + " ，定投金额：" + DT);
            totalDTMoney = totalDTMoney.add(DT);
            tmp = tmp.add(DT.divide(X, RoundingMode.HALF_DOWN));
        }
        System.out.println("总定投金额：" + totalDTMoney);
        System.out.println("最终亏损：" + lowValue.multiply(tmp).subtract(totalDTMoney));



        /*// 第一次定投金额已经当前净值
        BigDecimal DT1 = planMoney.multiply(rate0);
        BigDecimal X1 = highVal.subtract(highVal.multiply(new BigDecimal(0.02)));

        BigDecimal DT2 = DT1.multiply(rate);
        BigDecimal X2 = highVal.subtract(highVal.multiply(new BigDecimal(0.04)));

        BigDecimal DT3 = DT2.multiply(rate);
        BigDecimal X3 = highVal.subtract(highVal.multiply(new BigDecimal(0.06)));

        BigDecimal DT4 = DT3.multiply(rate);
        BigDecimal X4 = highVal.subtract(highVal.multiply(new BigDecimal(0.08)));

        BigDecimal DT5 = DT4.multiply(rate);
        BigDecimal X5 = highVal.subtract(highVal.multiply(new BigDecimal(0.1)));

        BigDecimal DT6 = DT5.multiply(rate);
        BigDecimal X6 = highVal.subtract(highVal.multiply(new BigDecimal(0.12)));

        BigDecimal DT7 = DT6.multiply(rate);
        BigDecimal X7 = highVal.subtract(highVal.multiply(new BigDecimal(0.14)));

        BigDecimal DT8 = DT7.multiply(rate);
        BigDecimal X8 = highVal.subtract(highVal.multiply(new BigDecimal(0.16)));

        BigDecimal DT9 = DT8.multiply(rate);
        BigDecimal X9 = highVal.subtract(highVal.multiply(new BigDecimal(0.18)));

        BigDecimal DT10 = DT9.multiply(rate);
        BigDecimal X10 = highVal.subtract(highVal.multiply(new BigDecimal(0.2)));

        BigDecimal totalMoney = DT1.add(DT2).add(DT3).add(DT4).add(DT5).add(DT6).add(DT7).add(DT8).add(DT9).add(DT10);
        System.out.println("总金额：" + totalMoney);
        System.out.println("=======================================================================================");

        System.out.println(X1.multiply(actualVal, MathContext.DECIMAL64) + " " + DT1);
        System.out.println(X2.multiply(actualVal, MathContext.DECIMAL64) + " " + DT1);
        System.out.println(X3.multiply(actualVal, MathContext.DECIMAL64) + " " + DT3);
        System.out.println(X4.multiply(actualVal, MathContext.DECIMAL64) + " " + DT4);
        System.out.println(X5.multiply(actualVal, MathContext.DECIMAL64) + " " + DT5);
        System.out.println(X6.multiply(actualVal, MathContext.DECIMAL64) + " " + DT6);
        System.out.println(X7.multiply(actualVal, MathContext.DECIMAL64) + " " + DT7);
        System.out.println(X8.multiply(actualVal, MathContext.DECIMAL64) + " " + DT8);
        System.out.println(X9.multiply(actualVal, MathContext.DECIMAL64) + " " + DT9);
        System.out.println(X10.multiply(actualVal, MathContext.DECIMAL64) + " " + DT10);



        System.out.println("=======================================================================================");

        // 最终累计亏损
        lowValue.multiply(DT1.divide(X1, RoundingMode.HALF_DOWN));

        // 合计
        BigDecimal tmp = BigDecimal.ZERO;
        tmp = tmp.add(DT1.divide(X1, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT2.divide(X2, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT3.divide(X3, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT4.divide(X4, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT5.divide(X5, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT6.divide(X6, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT7.divide(X7, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT8.divide(X8, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT9.divide(X9, RoundingMode.HALF_DOWN));
        tmp = tmp.add(DT10.divide(X10, RoundingMode.HALF_DOWN));
        BigDecimal KS = lowValue.multiply(tmp).subtract(totalMoney);

        System.out.println("累计亏损" + KS);*/
    }

    /**
     * 计算rate
     */
    public static BigDecimal calcRate(int times, BigDecimal firstMoneyPercent) {
        //System.out.println("============== 开始计算");
        if (firstMoneyPercent == null) {
            firstMoneyPercent = new BigDecimal(0.01);
        }
        BigDecimal money = new BigDecimal(10000);
        BigDecimal firstMoney = money.multiply(firstMoneyPercent);

        BigDecimal startRate = new BigDecimal(1.001);
        BigDecimal correctRate = startRate;
        BigDecimal correctMoney = null;
        for (BigDecimal rate = startRate; rate.compareTo(new BigDecimal(2)) < 0; rate = rate.add(new BigDecimal(0.001))) {
            BigDecimal totalMoney = BigDecimal.ZERO;
            BigDecimal lastMoney = firstMoney;
            for (int i = 1; i <= times; i++) {
                if (i == 1) {
                    totalMoney = totalMoney.add(firstMoney);
                } else {
                    lastMoney = lastMoney.multiply(rate);
                    totalMoney = totalMoney.add(lastMoney);
                }
            }
            //System.out.println(rate + " === " + totalMoney);
            if (correctMoney == null) {
                correctMoney = totalMoney;
            } else if (correctMoney.subtract(money).abs() .compareTo(totalMoney.subtract(money).abs()) > 0) {
                correctMoney = totalMoney;
                correctRate = rate;
            }
        }
        //System.out.println(correctMoney);
        //System.out.println(correctRate);
        return correctRate;
    }

    @Data
    class Vote {
        // 定投时的净值
        BigDecimal val;

        // 定投金额
        BigDecimal money;
    }
}

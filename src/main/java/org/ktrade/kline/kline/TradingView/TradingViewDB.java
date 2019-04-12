package org.ktrade.kline.kline.TradingView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TradingViewDB {
    private static List<TradingViewDataBean> db=new ArrayList<TradingViewDataBean>();


    static {
        init();
    }

    public static void init(){
        List<Long> tList = Arrays.asList(1481587200L, 1481673600L, 1481760000L, 1481846400L, 1482105600L, 1482192000L, 1482278400L, 1482364800L, 1482451200L, 1482796800L, 1482883200L, 1482969600L, 1483056000L, 1483401600L, 1483488000L, 1483574400L, 1483660800L, 1483920000L, 1484006400L, 1484092800L, 1484179200L, 1484265600L, 1484611200L, 1484697600L, 1484784000L, 1484870400L, 1485129600L, 1485216000L, 1485302400L, 1485388800L, 1485475200L, 1485734400L, 1485820800L, 1485907200L, 1485993600L, 1486080000L, 1486339200L, 1486425600L, 1486512000L, 1486598400L, 1486684800L, 1486944000L, 1487030400L, 1487116800L, 1487203200L, 1487289600L, 1487635200L, 1487721600L, 1487808000L, 1487894400L, 1488153600L, 1488240000L, 1488326400L, 1488412800L, 1488499200L, 1488758400L, 1488844800L, 1488931200L, 1489017600L, 1489104000L, 1489363200L, 1489449600L, 1489536000L, 1489622400L, 1489708800L, 1489968000L, 1490054400L, 1490140800L, 1490227200L, 1490313600L, 1490572800L, 1490659200L, 1490745600L, 1490832000L, 1490918400L, 1491177600L, 1491264000L, 1491350400L, 1491436800L, 1491523200L, 1491782400L, 1491868800L, 1491955200L, 1492041600L, 1492387200L, 1492473600L, 1492560000L, 1492646400L, 1492732800L, 1492992000L, 1493078400L, 1493164800L, 1493251200L, 1493337600L, 1493596800L, 1493683200L, 1493769600L, 1493856000L, 1493942400L, 1494201600L, 1494288000L, 1494374400L, 1494460800L, 1494547200L, 1494806400L, 1494892800L, 1494979200L, 1495065600L, 1495152000L, 1495411200L, 1495497600L, 1495584000L, 1495670400L, 1495756800L, 1496102400L, 1496188800L, 1496275200L, 1496361600L, 1496620800L, 1496707200L, 1496793600L, 1496880000L, 1496966400L, 1497225600L, 1497312000L, 1497398400L, 1497484800L, 1497571200L, 1497830400L, 1497916800L, 1498003200L, 1498089600L, 1498176000L, 1498435200L, 1498521600L, 1498608000L, 1498694400L, 1498780800L, 1499040000L, 1499212800L, 1499299200L, 1499385600L, 1499644800L, 1499731200L, 1499817600L, 1499904000L, 1499990400L, 1500249600L, 1500336000L, 1500422400L, 1500508800L, 1500595200L, 1500854400L, 1500940800L, 1501027200L, 1501113600L, 1501200000L, 1501459200L, 1501545600L, 1501632000L, 1501718400L, 1501804800L, 1502150400L, 1502236800L, 1502323200L, 1502409600L, 1502668800L, 1502755200L, 1502841600L, 1502928000L, 1503014400L, 1503273600L, 1503360000L, 1503446400L, 1503532800L, 1503619200L, 1503878400L, 1503964800L, 1504051200L, 1504137600L, 1504224000L, 1504569600L, 1504656000L, 1504742400L, 1504828800L, 1505088000L, 1505174400L, 1505260800L, 1505347200L, 1505433600L, 1505692800L, 1505779200L, 1505865600L, 1505952000L, 1506038400L, 1506297600L, 1506384000L, 1506470400L, 1506556800L, 1506643200L, 1506902400L, 1506988800L, 1507075200L, 1507161600L, 1507248000L, 1507507200L, 1507593600L, 1507680000L, 1507766400L, 1507852800L, 1508112000L, 1508198400L, 1508284800L, 1508371200L, 1508457600L, 1508716800L, 1508803200L, 1508889600L, 1508976000L, 1509062400L, 1509321600L, 1509408000L, 1509494400L, 1509580800L, 1509667200L, 1509926400L, 1510012800L, 1510185600L, 1510272000L, 1510531200L, 1510617600L, 1510704000L, 1510790400L, 1510876800L, 1511136000L, 1511222400L, 1511308800L, 1511481600L, 1511740800L, 1511827200L, 1511913600L, 1512000000L, 1512086400L, 1512345600L, 1512432000L, 1512518400L);

        Double[] odList = {113.84, 115.04, 115.38, 116.47, 115.8, 116.74, 116.8, 116.35, 115.59, 116.52, 117.52, 116.45, 116.65, 115.8, 115.85, 115.92, 116.78, 117.95, 118.77, 118.74, 118.895, 119.11, 118.34, 120.0, 119.4, 120.45, 120.0, 119.55, 120.42, 121.67, 122.14, 120.93, 121.15, 127.03, 127.975, 128.31, 129.13, 130.54, 131.35, 131.65, 132.46, 133.08, 133.47, 135.52, 135.67, 135.1, 136.23, 136.43, 137.38, 135.91, 137.14, 137.08, 137.89, 140.0, 138.78, 139.365, 139.06, 138.95, 138.74, 139.25, 138.85, 139.3, 139.41, 140.72, 141.0, 140.4, 142.11, 139.845, 141.26, 141.5, 139.39, 140.91, 143.68, 144.19, 143.72, 143.71, 143.25, 144.22, 144.29, 143.73, 143.6, 142.94, 141.6, 141.91, 141.48, 141.41, 141.88, 141.22, 142.44, 143.5, 143.91, 144.47, 143.9225, 144.09, 145.1, 147.54, 145.59, 146.52, 146.76, 149.03, 153.87, 153.63, 152.45, 154.7, 156.01, 155.94, 153.6, 151.27, 153.38, 154.0, 154.9, 153.84, 153.73, 154.0, 153.42, 153.97, 153.17, 153.58, 154.34, 153.9, 155.02, 155.25, 155.19, 145.74, 147.16, 147.5, 143.32, 143.78, 143.66, 146.87, 145.52, 145.77, 145.13, 147.17, 145.01, 144.49, 144.71, 144.45, 144.88, 143.69, 143.02, 142.9, 144.11, 144.73, 145.87, 145.5, 147.97, 148.82, 149.2, 150.48, 151.5, 149.99, 150.58, 151.8, 153.35, 153.75, 149.89, 149.9, 149.1, 159.28, 157.05, 156.07, 158.6, 159.26, 159.9, 156.6, 159.32, 160.66, 161.94, 160.52, 157.86, 157.5, 158.23, 159.07, 160.43, 159.65, 160.14, 160.1, 163.8, 163.64, 164.8, 163.75, 162.71, 162.09, 160.86, 160.5, 162.61, 159.87, 158.99, 158.47, 160.11, 159.51, 157.9, 155.8, 152.02, 149.99, 151.78, 153.8, 153.89, 153.21, 154.26, 154.01, 153.63, 154.18, 154.97, 155.81, 156.055, 155.97, 156.35, 156.73, 157.9, 159.78, 160.42, 156.75, 156.61, 156.89, 156.29, 156.91, 157.23, 159.29, 163.89, 167.9, 169.87, 167.64, 174.0, 172.365, 173.91, 175.11, 175.11, 173.5, 173.04, 169.97, 171.18, 171.04, 170.29, 170.78, 173.36, 175.1, 175.05, 174.3, 172.63, 170.43, 169.95, 172.48, 169.06, 167.5};
        List<BigDecimal> oList = new ArrayList<BigDecimal>();
        for (int i = 0; i < odList.length; i++) {
            oList.add(BigDecimal.valueOf(odList[i]));
        }

        Double[] cdList = {115.19, 115.19, 115.82, 115.97, 116.64, 116.95, 117.06, 116.29, 116.52, 117.26, 116.76, 116.73, 115.82, 116.15, 116.02, 116.61, 117.91, 118.99, 119.11, 119.75, 119.25, 119.04, 120.0, 119.99, 119.78, 120.0, 120.08, 119.97, 121.88, 121.94, 121.95, 121.63, 121.35, 128.75, 128.53, 129.08, 130.29, 131.53, 132.04, 132.42, 132.12, 133.29, 135.02, 135.51, 135.345, 135.72, 136.7, 137.11, 136.53, 136.66, 136.93, 136.99, 139.79, 138.96, 139.78, 139.34, 139.52, 139.0, 138.68, 139.14, 139.2, 138.99, 140.46, 140.69, 139.99, 141.46, 139.84, 141.42, 140.92, 140.64, 140.88, 143.8, 144.12, 143.93, 143.66, 143.7, 144.77, 144.02, 143.66, 143.34, 143.17, 141.63, 141.8, 141.05, 141.83, 141.2, 140.68, 142.44, 142.27, 143.64, 144.54, 143.6508, 143.79, 143.65, 146.6, 147.51, 147.06, 146.53, 148.96, 153.0, 153.96, 153.26, 153.95, 156.1, 155.7, 155.47, 150.25, 152.54, 152.96, 153.99, 153.8, 153.34, 153.87, 153.61, 153.67, 152.76, 153.18, 155.45, 153.93, 154.45, 155.37, 154.99, 148.98, 145.32, 146.59, 145.16, 144.29, 142.27, 146.34, 145.01, 145.87, 145.63, 146.35, 145.82, 143.74, 145.83, 143.68, 144.02, 143.5, 144.09, 142.73, 144.18, 145.06, 145.53, 145.74, 147.77, 149.04, 149.56, 150.08, 151.02, 150.34, 150.27, 152.09, 152.74, 153.46, 150.56, 149.5, 148.85, 150.05, 157.14, 155.57, 156.39, 160.08, 161.06, 155.27, 157.48, 159.85, 161.6, 160.95, 157.87, 157.5, 157.21, 159.78, 159.98, 159.27, 159.86, 161.47, 162.91, 163.35, 164.0, 164.05, 162.08, 161.91, 161.26, 158.63, 161.5, 160.82, 159.65, 158.28, 159.88, 158.67, 158.73, 156.07, 153.39, 151.89, 150.55, 153.14, 154.23, 153.28, 154.12, 153.81, 154.48, 153.4508, 155.39, 155.3, 155.84, 155.9, 156.55, 156.0, 156.99, 159.88, 160.47, 159.76, 155.98, 156.16, 156.17, 157.1, 156.405, 157.41, 163.05, 166.72, 169.04, 166.89, 168.11, 172.5, 174.25, 174.81, 175.88, 174.67, 173.97, 171.34, 169.08, 171.1, 170.15, 169.98, 173.14, 174.96, 174.97, 174.09, 173.07, 169.48, 171.85, 171.05, 169.8, 169.64, 169.01};
        List<BigDecimal> cList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cdList.length; i++) {
            cList.add(BigDecimal.valueOf(cdList[i]));
        }

        Double[] hdList = {115.92, 116.2, 116.73, 116.5, 117.38, 117.5, 117.4, 116.51, 116.52, 117.8, 118.0166, 117.1095, 117.2, 116.33, 116.51, 116.8642, 118.16, 119.43, 119.38, 119.93, 119.3, 119.62, 120.24, 120.5, 120.09, 120.45, 120.81, 120.1, 122.1, 122.44, 122.35, 121.63, 121.39, 130.49, 129.39, 129.19, 130.5, 132.09, 132.22, 132.445, 132.94, 133.82, 135.09, 136.27, 135.9, 135.83, 136.75, 137.12, 137.48, 136.66, 137.435, 137.435, 140.15, 140.2786, 139.83, 139.77, 139.98, 139.8, 138.79, 139.3571, 139.43, 139.65, 140.7501, 141.02, 141.0, 141.5, 142.8, 141.6, 141.5844, 141.74, 141.22, 144.04, 144.49, 144.5, 144.27, 144.12, 144.89, 145.46, 144.52, 144.18, 143.8792, 143.35, 142.15, 142.38, 141.88, 142.04, 142.0, 142.92, 142.68, 143.95, 144.9, 144.6, 144.16, 144.3, 147.2, 148.09, 147.49, 147.14, 148.98, 153.7, 154.88, 153.94, 154.07, 156.42, 156.65, 156.06, 154.57, 153.34, 153.98, 154.58, 154.9, 154.17, 154.35, 154.24, 154.43, 154.17, 153.33, 155.45, 154.45, 155.81, 155.98, 155.54, 155.19, 146.09, 147.45, 147.5, 144.4798, 144.5, 146.74, 146.87, 146.0693, 146.7, 147.16, 148.28, 146.16, 146.11, 145.13, 144.96, 145.3001, 144.79, 143.5, 144.75, 145.95, 145.85, 146.18, 148.49, 149.33, 150.9, 150.13, 151.42, 151.74, 150.44, 152.44, 153.84, 153.93, 153.99, 150.23, 150.33, 150.22, 159.75, 157.21, 157.4, 161.83, 161.27, 160.0, 158.5728, 160.21, 162.195, 162.51, 160.71, 159.5, 157.89, 160.0, 160.47, 160.74, 160.56, 162.0, 163.12, 163.89, 164.52, 164.94, 164.25, 162.99, 162.24, 161.15, 162.05, 163.96, 159.96, 159.4, 160.97, 160.5, 159.77, 158.26, 155.8, 152.27, 151.83, 153.92, 154.7189, 154.28, 154.13, 154.45, 155.09, 153.86, 155.44, 155.49, 156.73, 158.0, 156.98, 157.37, 157.28, 160.0, 160.87, 160.71, 157.08, 157.75, 157.69, 157.42, 157.55, 157.8295, 163.6, 168.07, 169.6499, 169.94, 168.5, 174.26, 174.99, 175.25, 176.095, 175.38, 174.5, 173.48, 170.3197, 171.87, 171.39, 170.56, 173.7, 175.0, 175.5, 175.08, 174.87, 172.92, 172.14, 171.67, 172.62, 171.52, 170.2047};
        List<BigDecimal> hList = new ArrayList<BigDecimal>();
        for (int i = 0; i < hdList.length; i++) {
            hList.add(BigDecimal.valueOf(hdList[i]));
        }

        Double[] ldList = {113.75, 114.98, 115.23, 115.645, 115.75, 116.68, 116.78, 115.64, 115.59, 116.49, 116.2, 116.4, 115.43, 114.76, 115.75, 115.81, 116.47, 117.94, 118.3, 118.6, 118.21, 118.81, 118.22, 119.71, 119.37, 119.7346, 119.77, 119.5, 120.28, 121.6, 121.6, 120.66, 120.62, 127.01, 127.78, 128.16, 128.9, 130.45, 131.22, 131.12, 132.05, 132.75, 133.25, 134.62, 134.8398, 135.1, 135.98, 136.11, 136.3, 135.28, 136.28, 136.7, 137.595, 138.76, 138.59, 138.5959, 138.79, 138.82, 137.05, 138.64, 138.82, 138.84, 139.025, 140.26, 139.89, 140.23, 139.73, 139.76, 140.61, 140.35, 138.62, 140.62, 143.19, 143.5, 143.01, 143.05, 143.17, 143.81, 143.45, 143.27, 142.9, 140.06, 141.01, 141.05, 140.87, 141.11, 140.45, 141.16, 141.85, 143.18, 143.87, 143.3762, 143.31, 143.27, 144.96, 146.84, 144.27, 145.81, 146.76, 149.03, 153.45, 152.11, 152.31, 154.67, 155.05, 154.72, 149.71, 151.13, 152.63, 152.91, 153.31, 152.67, 153.03, 153.31, 153.33, 152.38, 152.22, 152.89, 153.46, 153.78, 154.48, 154.4, 146.02, 142.51, 145.15, 143.84, 142.21, 142.2, 143.66, 144.94, 144.61, 145.1199, 145.11, 145.38, 143.62, 143.1601, 142.28, 143.78, 143.1, 142.7237, 142.41, 142.9, 143.37, 144.38, 144.82, 145.44, 147.33, 148.57, 148.67, 149.95, 150.19, 148.88, 149.9, 151.8, 153.06, 147.3, 149.19, 148.13, 148.41, 156.16, 155.02, 155.69, 158.27, 159.11, 154.63, 156.07, 158.75, 160.14, 160.15, 157.84, 156.72, 155.1101, 158.02, 158.88, 158.55, 159.27, 159.93, 160.0, 162.61, 163.48, 163.63, 160.56, 160.52, 160.36, 158.53, 159.89, 158.77, 157.91, 158.09, 158.0, 157.995, 158.44, 153.83, 152.75, 150.56, 149.16, 151.69, 153.54, 152.7, 152.0, 152.72, 153.91, 152.46, 154.05, 154.56, 155.485, 155.1, 155.75, 155.7299, 156.41, 157.65, 159.23, 159.6, 155.02, 155.96, 155.5, 156.2, 155.27, 156.78, 158.7, 163.72, 166.94, 165.61, 165.28, 171.12, 171.72, 173.6, 173.14, 174.27, 173.4, 171.18, 168.38, 170.3, 169.64, 169.56, 170.78, 173.05, 174.6459, 173.34, 171.86, 167.16, 168.44, 168.5, 169.63, 168.4, 166.46};
        List<BigDecimal> lList = new ArrayList<BigDecimal>();
        for (int i = 0; i < ldList.length; i++) {
            lList.add(BigDecimal.valueOf(ldList[i]));
        }

        Double[] vdList = {43733811.0, 34031834.0, 46524544.0, 44351134.0, 27779423.0, 21424965.0, 23783165.0, 26085854.0, 14249484.0, 18296855.0, 20905892.0, 15039519.0, 30586265.0, 28781865.0, 21118116.0, 22193587.0, 31751900.0, 33561948.0, 24462051.0, 27588593.0, 27086220.0, 26111948.0, 34439843.0, 23712961.0, 25597291.0, 32597892.0, 22050218.0, 23211038.0, 32586673.0, 26337576.0, 20562944.0, 30377503.0, 49200993.0, 111985040.0, 33710411.0, 24507301.0, 26845924.0, 38183841.0, 23004072.0, 28349859.0, 20065458.0, 23035421.0, 33226223.0, 35623100.0, 22584555.0, 22198197.0, 24507156.0, 20836932.0, 20788186.0, 21776585.0, 20257426.0, 23482860.0, 36414585.0, 26210984.0, 21571121.0, 21750044.0, 17446297.0, 18707236.0, 22155904.0, 19612801.0, 17421717.0, 15309065.0, 25691774.0, 19231998.0, 43884952.0, 21542038.0, 39529912.0, 25860165.0, 20346301.0, 22395563.0, 23575094.0, 33374805.0, 29189955.0, 21207252.0, 19661651.0, 19985714.0, 19891354.0, 27717854.0, 21149034.0, 16658543.0, 18933397.0, 30379376.0, 20350000.0, 17822880.0, 16582094.0, 14697544.0, 17328375.0, 23319562.0, 17320928.0, 17116599.0, 18216472.0, 19614287.0, 13948980.0, 20247187.0, 32818760.0, 39752670.0, 45142806.0, 23275690.0, 26787359.0, 48339210.0, 35942435.0, 25670456.0, 25596687.0, 32221756.0, 25700983.0, 19904679.0, 49482818.0, 33159664.0, 26733798.0, 22340069.0, 19430358.0, 19118319.0, 19044463.0, 21632202.0, 20034934.0, 23162873.0, 16180143.0, 27285861.0, 24803858.0, 26249630.0, 20678772.0, 20771367.0, 64176149.0, 71563614.0, 33749154.0, 31224203.0, 31348832.0, 49180748.0, 31449132.0, 24572170.0, 21064679.0, 18673365.0, 25997976.0, 25524661.0, 24423643.0, 21915939.0, 31116980.0, 22328979.0, 14276812.0, 20758795.0, 23374374.0, 18505351.0, 21030466.0, 18311156.0, 23617964.0, 24922788.0, 19961788.0, 23243713.0, 17713795.0, 20615419.0, 17053326.0, 24671002.0, 21122730.0, 18612649.0, 15172136.0, 32175875.0, 16832947.0, 19422655.0, 24725526.0, 69222793.0, 26000738.0, 20349532.0, 35775675.0, 25640394.0, 39081017.0, 25943187.0, 21754810.0, 27936774.0, 27321761.0, 26925694.0, 27012525.0, 26145653.0, 21297812.0, 19198189.0, 19029621.0, 25015218.0, 25279674.0, 29307862.0, 26973946.0, 26412439.0, 16508568.0, 29317054.0, 21179047.0, 21722995.0, 28183159.0, 31028926.0, 71139119.0, 44393752.0, 23073646.0, 48203642.0, 27939718.0, 20347352.0, 51693239.0, 36643382.0, 46114424.0, 43922334.0, 35470985.0, 24959552.0, 21896592.0, 25856530.0, 18524860.0, 16146388.0, 19844177.0, 21032800.0, 16423749.0, 16200129.0, 15456331.0, 16607693.0, 16045720.0, 16287608.0, 23894630.0, 18816438.0, 16158659.0, 42111326.0, 23612246.0, 21654461.0, 17137731.0, 20126554.0, 16751691.0, 43904150.0, 43923292.0, 35474672.0, 33100847.0, 32710040.0, 58683826.0, 34242566.0, 23910914.0, 28636531.0, 25061183.0, 16828025.0, 23588451.0, 28702351.0, 23497326.0, 21665811.0, 15974387.0, 24875471.0, 24997274.0, 14026519.0, 20536313.0, 25468442.0, 40788324.0, 40172368.0, 39590080.0, 32115052.0, 27008428.0, 28224357.0};
        List<BigDecimal> vList = new ArrayList<BigDecimal>();
        for (int i = 0; i < vdList.length; i++) {
            vList.add(BigDecimal.valueOf(vdList[i]));
        }
        Long tx=1512962579L-oList.size()/3*20;
        for (int i = 0, size = oList.size(); i < size; i++) {
            tx+=20L;
            TradingViewDataBean bean = new TradingViewDataBean();
            bean.setS("ok");
//            bean.setT(tList.get(i));
            bean.setT(tx);
            bean.setC(cList.get(i));
            bean.setO(oList.get(i));
            bean.setH(hList.get(i));
            bean.setL(lList.get(i));
            bean.setV(vList.get(i));
            db.add(bean);
        }
        //System.out.printf("last time:"+tx);
    }
    public static List<TradingViewDataBean> search(Long from ,Long to){
        if(from==null || to ==null){
            return null;
        }
        List<TradingViewDataBean> list=new ArrayList<TradingViewDataBean>();
        for(int i=0,size=db.size();i<size;i++){
            TradingViewDataBean bean=db.get(i);
            if(bean.getT()>=from && bean.getT()<=to){
                list.add(bean);
            }
        }
        return list;
    }

    public static TradingViewHistory convert(List<TradingViewDataBean> dbList){
        TradingViewHistory history=new TradingViewHistory();
        if(dbList==null || dbList.size()==0){
            history.setS("no_data");
            return history;
        }



        List<Long> t= new ArrayList<Long>();
        List<BigDecimal> c =new ArrayList<BigDecimal>();
        List<BigDecimal> o =new ArrayList<BigDecimal>();
        List<BigDecimal> h =new ArrayList<BigDecimal>();
        List<BigDecimal> l =new ArrayList<BigDecimal>();
        List<BigDecimal> v =new ArrayList<BigDecimal>();

        for(int i=0,size=dbList.size();i<size;i++){
            TradingViewDataBean bean=dbList.get(i);
            t.add(bean.getT());
            c.add(bean.getC());
            o.add(bean.getO());
            h.add(bean.getH());
            l.add(bean.getL());
            v.add(bean.getV());
        }

        history.setS("ok");
        history.setT(t);
        history.setC(c);
        history.setO(o);
        history.setH(h);
        history.setL(l);
        history.setV(v);

        return history;
    }

    public static void main(String[] args) {
        //System.out.println( TradingViewDB.db.size());
        List<TradingViewDataBean> search = TradingViewDB.search(1481511999L, 1512616059L);
        TradingViewHistory convert = TradingViewDB.convert(search);
        System.out.println(convert);
    }
}
package com.mengzhilan.sysout;

/**
 * Create by xlp on 2020/12/2
 * <p>
 * 代替到处的System.out.println()输出
 */
public class ReportUtils {
    /**
     * 标记是否启动System.out.println()；输出信息
     * 值为true表示启动，false表示不启动
     */
    private static boolean isReport = true;

    /**
     * 输出提示信息
     *
     * @param msg
     */
    public static void report(String msg) {
        if (isReport)
            System.out.println(msg);
    }

    /**
     * 输出提示信息
     *
     * @param object
     */
    public static void report(Object object) {
        if (isReport)
            System.out.println(object);
    }

    /**
     * 输出错误信息
     *
     * @param e
     */
    public static void report(Throwable e) {
        if (isReport)
           e.printStackTrace();
    }

    /**
     * 设置是否启动System.out.println()；输出信息
     * 值为true表示启动，false表示不启动
     *
     * @param isReport
     */
    public static void setIsReport(boolean isReport) {
        ReportUtils.isReport = isReport;
    }
}

package com.lswq.app.controller.report;

public interface ReportEnum {


    enum ReportType {

        //  日报
        DAY_REPORT("day_report"),

        //  周报
        WEEK_REPORT("week_report"),

        //  月报
        MONTH_REPORT("month_report");

        private String name;


        /**
         * @param name
         */
        private ReportType(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }

    }

}

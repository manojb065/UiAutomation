package com.fampay.automation.util.test;



import com.fampay.automation.util.config.ConfigFactory;
import com.fampay.automation.util.email.ReportEmailSender;
import com.fampay.automation.util.helper.FileUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class TestRunner  {

    public static void main(String[] args) throws IOException, ParseException {
        ReportEmailSender email = new ReportEmailSender();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = "9 Apr";
        String date1 = "09 Apr 2023";

        //convert String to LocalDate

        System.out.println(LocalDate.parse(date+" "+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")),
                DateTimeFormatter.ofPattern("d MMM yyyy")).compareTo(LocalDate.parse(date1,DateTimeFormatter.ofPattern("d MMM yyyy"))));
//        System.out.println(LocalDate.now().format(new"dd MMM YYYY"));
    }

}

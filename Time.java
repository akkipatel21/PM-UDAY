package com.ddaRest.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddaRest.models.PaymentStatus;
import com.ddaRest.service.PaymentService;
import com.ddaRest.service.TimeAllocationService;

@Controller
public class Time {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TimeAllocationService timeAllocationService;

    @RequestMapping(value = "/getAppointment", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody PaymentStatus savePaymentStatus(@RequestBody PaymentStatus paymentStatus) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int i=1;
//            for (int i = 1 ; i >= 0  ; i++) {
             while (i!=0) {
                Date datey = new Date();
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(datey);
                cal1.add(Calendar.DAY_OF_YEAR, i);
                Date tomorrow1 = cal1.getTime();
                String datetimxe = dateFormat.format(tomorrow1);
                int count = paymentService.getDateCount(datetimxe);
                int holidays = timeAllocationService.getDayCount(datetimxe);
                if (count != 0 && count <= 15 && holidays!=1) {
                    paymentStatus.setDate(datetimxe);
                    switch (count) {
                    case 0:
                        paymentStatus.setTime("10.00 AM");
                        break;
                    case 1:
                        paymentStatus.setTime("10.30 AM");
                        break;
                    case 2:
                        paymentStatus.setTime("11.00 AM");
                        break;
                    case 3:
                        paymentStatus.setTime("11.30 AM");
                        break;
                    case 4:
                        paymentStatus.setTime("12.00 AM");
                        break;
                    case 5:
                        paymentStatus.setTime("12.30 PM");
                        break;
                    case 6:
                        paymentStatus.setTime("1.00 PM");
                        break;
                    case 7:
                        paymentStatus.setTime("1.30 PM");
                        break;
                    }
                    paymentService.save(paymentStatus);
                    return paymentStatus;
                }
                else if(count == 0 && holidays == 0){
                    paymentStatus.setDate(datetimxe);
                    paymentStatus.setTime("10.10 AM");
                    paymentService.save(paymentStatus);
                    return paymentStatus;
                }
               i++;
            }
             
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return paymentStatus;
    }
}

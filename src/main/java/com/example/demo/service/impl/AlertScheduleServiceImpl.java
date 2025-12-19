// // package com.example.demo.service.impl;

// // import com.example.demo.entity.AlertSchedule;
// // import com.example.demo.entity.Warranty;
// // import com.example.demo.repository.AlertScheduleRepository;
// // import com.example.demo.repository.WarrantyRepository;
// // import com.example.demo.service.AlertScheduleService;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;

// // import java.util.List;

// // @Service
// // public class AlertScheduleServiceImpl implements AlertScheduleService {

// //     @Autowired private AlertScheduleRepository alertScheduleRepository;
// //     @Autowired private WarrantyRepository warrantyRepository;

// //     @Override
// //     public AlertSchedule createSchedule(Long warrantyId, AlertSchedule schedule) {
// //         Warranty warranty = warrantyRepository.findById(warrantyId).get();
// //         schedule.setWarranty(warranty);
// //         return alertScheduleRepository.save(schedule);
// //     }

// //     @Override
// //     public List<AlertSchedule> getSchedules(Long warrantyId) {
// //         return alertScheduleRepository.findAll();
// //     }
// // }

// package com.example.demo.service.impl;

// import com.example.demo.entity.AlertSchedule;
// import com.example.demo.entity.Warranty;
// import com.example.demo.repository.AlertScheduleRepository;
// import com.example.demo.repository.WarrantyRepository;
// import com.example.demo.service.AlertScheduleService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class AlertScheduleServiceImpl implements AlertScheduleService {

//     @Autowired private AlertScheduleRepository alertScheduleRepository;
//     @Autowired private WarrantyRepository warrantyRepository;

//     @Override
//     public AlertSchedule createSchedule(Long warrantyId, AlertSchedule schedule) {
//         Warranty warranty = warrantyRepository.findById(warrantyId).get();
//         schedule.setWarranty(warranty);
//         return alertScheduleRepository.save(schedule);
//     }

//     @Override
//     public List<AlertSchedule> getSchedules(Long warrantyId) {
//         return alertScheduleRepository.findAll();
//     }
// }

// src/main/java/com/example/demo/service/impl/AlertScheduleServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.entity.Warranty;
import com.example.demo.repository.AlertScheduleRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertScheduleService;

import java.util.List;

public class AlertScheduleServiceImpl implements AlertScheduleService {

    private final AlertScheduleRepository scheduleRepository;
    private final WarrantyRepository warrantyRepository;

    public AlertScheduleServiceImpl(AlertScheduleRepository scheduleRepository,
                                    WarrantyRepository warrantyRepository) {
        this.scheduleRepository = scheduleRepository;
        this.warrantyRepository = warrantyRepository;
    }

    @Override
    public AlertSchedule createSchedule(Long warrantyId, AlertSchedule schedule) {
        Warranty w = warrantyRepository.findById(warrantyId).orElseThrow(() -> new RuntimeException("Warranty not found"));
        if (schedule.getDaysBeforeExpiry() == null || schedule.getDaysBeforeExpiry() < 0) {
            throw new IllegalArgumentException("daysBeforeExpiry must be non-negative");
        }
        schedule.setWarrantyId(w.getId());
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<AlertSchedule> getSchedules(Long warrantyId) {
        warrantyRepository.findById(warrantyId).orElseThrow(() -> new RuntimeException("Warranty not found"));
        return scheduleRepository.findByWarrantyId(warrantyId);
    }
}
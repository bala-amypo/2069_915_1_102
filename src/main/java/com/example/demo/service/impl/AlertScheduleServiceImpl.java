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

package com.example.demo.service.impl;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.entity.Warranty;
import com.example.demo.repository.AlertScheduleRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertScheduleServiceImpl implements AlertScheduleService {

    @Autowired private AlertScheduleRepository alertScheduleRepository;
    @Autowired private WarrantyRepository warrantyRepository;

    @Override
    public AlertSchedule createSchedule(Long warrantyId, AlertSchedule schedule) {
        Warranty warranty = warrantyRepository.findById(warrantyId).get();
        schedule.setWarranty(warranty);
        return alertScheduleRepository.save(schedule);
    }

    @Override
    public List<AlertSchedule> getSchedules(Long warrantyId) {
        return alertScheduleRepository.findAll();
    }
}

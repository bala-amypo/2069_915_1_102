// // AlertLogServiceImpl.java
// package com.example.demo.service.impl;

// import com.example.demo.entity.AlertLog;
// import com.example.demo.entity.Warranty;
// import com.example.demo.repository.AlertLogRepository;
// import com.example.demo.repository.WarrantyRepository;
// import com.example.demo.service.AlertLogService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class AlertLogServiceImpl implements AlertLogService {

//     @Autowired
//     private AlertLogRepository alertLogRepository;

//     @Autowired
//     private WarrantyRepository warrantyRepository;

//     @Override
//     public AlertLog addLog(Long warrantyId, String message) {
//         Warranty warranty = warrantyRepository.findById(warrantyId)
//                 .orElseThrow(() -> new RuntimeException("Warranty not found"));

//         AlertLog log = new AlertLog();
//         log.setWarranty(warranty);
//         log.setMessage(message);

//         return alertLogRepository.save(log);
//     }

//     @Override
//     public List<AlertLog> getLogs(Long warrantyId) {
//         Warranty warranty = warrantyRepository.findById(warrantyId)
//                 .orElseThrow(() -> new RuntimeException("Warranty not found"));

//         return alertLogRepository.findAll()
//                 .stream()
//                 .filter(l -> l.getWarranty().equals(warranty))
//                 .toList();
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.AlertLog;
import com.example.demo.entity.Warranty;
import com.example.demo.repository.AlertLogRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertLogServiceImpl implements AlertLogService {

    @Autowired
    private AlertLogRepository alertLogRepository;

    @Autowired
    private WarrantyRepository warrantyRepository;

    @Override
    public AlertLog addLog(Long warrantyId, String message) {
        Warranty warranty = warrantyRepository.findById(warrantyId).get();
        AlertLog log = new AlertLog();
        log.setWarranty(warranty);
        log.setMessage(message);
        return alertLogRepository.save(log);
    }

    @Override
    public List<AlertLog> getLogs(Long warrantyId) {
        return alertLogRepository.findAll();
    }
}
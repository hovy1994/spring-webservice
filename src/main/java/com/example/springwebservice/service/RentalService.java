package com.example.springwebservice.service;

import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.cabinet.CabinetRepository;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.item.ItemRepository;
import com.example.springwebservice.domain.item.RentalRequestInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class RentalService {
    private CabinetRepository cabinetRepository;
    private ItemRepository itemRepository;

    public ArrayList<Cabinet> matchCabinet(ArrayList<Integer> availableCabinetList){
        ArrayList<Cabinet> cabinets=new ArrayList<Cabinet>();
        List<Cabinet> cabinetList = cabinetRepository.findAll();
        for(Integer idx: availableCabinetList){
            for(Cabinet c: cabinetList){
                if(c.getCABINET_IDX()==idx){
                    cabinets.add(c);
                }
            }
        }
        return cabinets;
    }
//    public ArrayList<Integer> findCabinet(RentalRequestInfo info){
//        List<Item> itemList = itemRepository.findAll();
//        List<Cabinet> cabinetList = cabinetRepository.findAll();
//        ArrayList<Integer> availableCabinetList= new ArrayList<Integer>();
//
//        Integer cabinet[]=new Integer[cabinetList.size()+1];
//
//        for (Item item:itemList){
//            if(item.getCATEGORY_IDX()!=info.getCATEGORY_IDX()) continue;
//            if(item.getSTART_TIME().isBefore(info.getSTART_TIME()) &&info.getEND_TIME().isAfter(item.getEND_TIME())) continue;
//            else if(info.getSTART_TIME().isBefore(item.getEND_TIME())&&item.getEND_TIME().isBefore(info.getEND_TIME())) continue;
//            else if(info.getSTART_TIME().isBefore(item.getSTART_TIME())&&item.getSTART_TIME().isBefore(info.getEND_TIME())) continue;
//            else{
//                Integer cabinet_idx=item.getCABINET_IDX();
//                cabinet[cabinet_idx]++;
//                if(cabinet[cabinet_idx]==1){
//                    availableCabinetList.add(cabinet_idx);
//                }
//            }
//        }
//
//        Integer minIdx=1, minVal=itemList.size();
//        Integer maxIdx=1,maxVal=1;
//        for (int i=0;i<cabinetList.size();i++){
//            if(minVal>cabinet[i]){
//                minVal=cabinet[i];
//                minIdx=i;
//            }
//            if(maxVal<cabinet[i]){
//                maxVal=cabinet[i];
//                maxIdx=i;
//            }
//        }
//        availableCabinetList.add(minIdx);
//        availableCabinetList.add(maxIdx);
//        //return matchCabinet(availableCabinetList);
//        return availableCabinetList;
//    }

    public Integer findCabinet(RentalRequestInfo info){
        List<Item> itemList = itemRepository.findAll();
        List<Cabinet> cabinetList = cabinetRepository.findAll();
        ArrayList<Integer> availableCabinetList= new ArrayList<Integer>();

        Integer i=0;

        for(Item item: itemList){
            if(i<item.getITEM_IDX()){
                i=item.getITEM_IDX();
            }
        }
        i=info.getCategory_idx();

        return i;
    }
}

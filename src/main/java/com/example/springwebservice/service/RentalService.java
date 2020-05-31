package com.example.springwebservice.service;

import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.cabinet.CabinetRepository;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.item.ItemRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private CabinetRepository cabinetRepository;
    private ItemRepository itemRepository;

    public ArrayList<Item> findCabinet(LocalDateTime start,LocalDateTime end){
        List<Item> itemList = itemRepository.findAll();
        ArrayList<Item> availableItemList= new ArrayList<Item>();
        //List<Item> availableItemList=new List<Item>();
        //List<Cabinet> availableCabinetList;

        for (Item item:itemList){
            if(item.getSTART_TIME().isBefore(start) &&end.isAfter(item.getEND_TIME())) continue;
            else if(start.isBefore(item.getEND_TIME())&&item.getEND_TIME().isBefore(end)) continue;
            else if(start.isBefore(item.getSTART_TIME())&&item.getSTART_TIME().isBefore(end)) continue;
            else{
                availableItemList.add(item);
            }
        }
        return availableItemList;
    }

}

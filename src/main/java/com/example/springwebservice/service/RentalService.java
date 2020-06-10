package com.example.springwebservice.service;

import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.cabinet.CabinetRepository;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.item.ItemRepository;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
import com.example.springwebservice.domain.member.MemberRepository;
import com.example.springwebservice.service.mapper.ItemMapper;
import com.example.springwebservice.service.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class RentalService {
    private CabinetRepository cabinetRepository;
    private ItemRepository itemRepository;
    private MemberRepository memberRepository;

    @Autowired
    private ItemMapper itemMapper;
    private MemberMapper memberMapper;

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


    public ArrayList<Cabinet> findCabinet(RentalRequestInfo info){
        List<Item> itemList = itemRepository.findAll();
        List<Cabinet> cabinetList = cabinetRepository.findAll();
        ArrayList<Integer> availableCabinetList= new ArrayList<Integer>();

        int cabinet[]=new int[cabinetList.size()+1];
        for (Item item:itemList){
            if(item.getCATEGORY_IDX()!=info.getCategory_idx()) continue;
            if(item.getSTART_TIME().isBefore(info.getStart()) &&info.getEnd().isAfter(item.getEND_TIME())) continue;
            else if(info.getStart().isBefore(item.getEND_TIME())&&item.getEND_TIME().isBefore(info.getEnd())) continue;
            else if(info.getStart().isBefore(item.getSTART_TIME())&&item.getSTART_TIME().isBefore(info.getEnd())) continue;
            else{
                Integer cabinet_idx=item.getCABINET_IDX();
                cabinet[cabinet_idx]++;
                if(cabinet[cabinet_idx]==1){
                    availableCabinetList.add(cabinet_idx);
                }
            }
        }
        Integer minIdx=1, minVal=itemList.size();
        Integer maxIdx=1,maxVal=1;
        for (int i=1;i<cabinetList.size()+1;i++){
            if(minVal>cabinet[i]){
                minVal=cabinet[i];
                minIdx=i;
            }
            if(maxVal<cabinet[i]){
                maxVal=cabinet[i];
                maxIdx=i;
            }
        }
        availableCabinetList.add(minIdx);
        availableCabinetList.add(maxIdx);
        return matchCabinet(availableCabinetList);
    }
    public List<Item> findItemList(Integer cabinetIdx){
        List<Item> itemList= itemMapper.findAll(cabinetIdx);

        return itemList;
    }
    public Item findAvailableItem(RentalRequestInfo info){
        System.out.print("Cabinet idx: "+info.getStart_cabinet_idx());
        List<Item> itemList=findItemList(info.getStart_cabinet_idx());
        System.out.print("itemList size: "+itemList.size());

        for (Item item:itemList){
            if(item.getSTART_TIME().isBefore(info.getStart()) &&info.getEnd().isAfter(item.getEND_TIME())) continue;
            else if(info.getStart().isBefore(item.getEND_TIME())&&item.getEND_TIME().isBefore(info.getEnd())) continue;
            else if(info.getStart().isBefore(item.getSTART_TIME())&&item.getSTART_TIME().isBefore(info.getEnd())) continue;
            else{
                return item;
            }
        }
        return null;
    }

}

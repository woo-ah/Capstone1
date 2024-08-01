package com.example.capstone1.service.impl;

import com.example.capstone1.domain.Tbpostcmt;
import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.TbpostcmtDto;
import com.example.capstone1.mapper.TbpostcmtMapper;
import com.example.capstone1.repository.TbpostcmtRepository;
import com.example.capstone1.service.TbpostcmtService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostcmtServiceImpl implements TbpostcmtService {

    private final TbpostcmtRepository tbpostcmtRepository;
    private final TbpostcmtMapper tbpostcmtMapper;
    public TbpostcmtServiceImpl(
            TbpostcmtRepository tbpostcmtRepository
            ,TbpostcmtMapper tbpostcmtMapper
    ) {
        this.tbpostcmtRepository = tbpostcmtRepository;
        this.tbpostcmtMapper = tbpostcmtMapper;
    }


    @Override
    public TbpostcmtDto.CreateResDto create(TbpostcmtDto.CreateReqDto param){
        return tbpostcmtRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbpostcmtDto.CreateResDto update(TbpostcmtDto.UpdateReqDto param){
        Tbpostcmt tbpostcmt = tbpostcmtRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbpostcmt.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbpostcmt.setProcess(param.getProcess());
        }
        tbpostcmtRepository.save(tbpostcmt);
        return tbpostcmt.toCreateResDto();
    }
    @Override
    public TbpostcmtDto.CreateResDto delete(TbpostcmtDto.UpdateReqDto param){
        Tbpostcmt tbpostcmt = tbpostcmtRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbpostcmtRepository.delete(tbpostcmt);
        return TbpostcmtDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbpostcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbpostcmtDto.DetailResDto selectResDto = tbpostcmtMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbpostcmtDto.DetailResDto> list(TbpostcmtDto.ListReqDto param){
        return detailList(tbpostcmtMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbpostcmtDto.PagedListReqDto param) {
        int[] res = param.init(tbpostcmtMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbpostcmtMapper.pagedList(param)));
    }
    @Override
    public List<TbpostcmtDto.DetailResDto> scrollList(TbpostcmtDto.ScrollListReqDto param){
        param.init();
        return detailList(tbpostcmtMapper.scrollList(param));
    }

    public List<TbpostcmtDto.DetailResDto> detailList(List<TbpostcmtDto.DetailResDto> list){
        List<TbpostcmtDto.DetailResDto> newList = new ArrayList<>();
        for(TbpostcmtDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}

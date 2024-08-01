package com.example.capstone1.service.impl;


import com.example.capstone1.domain.Tbpost;
import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.TbpostDto;
import com.example.capstone1.dto.TbpostfileDto;
import com.example.capstone1.mapper.TbpostMapper;
import com.example.capstone1.repository.TbpostRepository;
import com.example.capstone1.service.TbpostService;
import com.example.capstone1.service.TbpostfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostServiceImpl implements TbpostService {

    private final TbpostRepository tbpostRepository;
    private final TbpostMapper tbpostMapper;
    private final TbpostfileService tbpostfileService;
    public TbpostServiceImpl(
            TbpostRepository tbpostRepository
            , TbpostMapper tbpostMapper
            , TbpostfileService tbpostfileService
    ) {
        this.tbpostRepository = tbpostRepository;
        this.tbpostMapper = tbpostMapper;
        this.tbpostfileService = tbpostfileService;
    }

    @Override
    public TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param){
        TbpostDto.CreateResDto createResDto = tbpostRepository.save(param.toEntity()).toCreateResDto();

        for(int i=0;i<param.getTbpostfileUrls().size();i++){
            tbpostfileService.create(
                    TbpostfileDto.CreateReqDto.builder()
                            .tbpostId(createResDto.getId())
                            .type(param.getTbpostfileTypes().get(i))
                            .url(param.getTbpostfileUrls().get(i))
                            .build()
            );
        }

        return createResDto;
    }

    @Override
    public TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param){
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbpost.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbpost.setProcess(param.getProcess());
        }

        if(param.getTbuserId() != null){
            tbpost.setTbuserId(param.getTbuserId());
        }
        if(param.getTitle() != null){
            tbpost.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbpost.setContent(param.getContent());
        }
        tbpostRepository.save(tbpost);
        return tbpost.toCreateResDto();
    }

    @Override
    public TbpostDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbpostDto.DetailResDto selectResDto = tbpostMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }

        selectResDto.setTbpostfiles(
                tbpostfileService.list(TbpostfileDto.ListReqDto.builder().tbpostId(selectResDto.getId()).build())
        );

        return selectResDto;
    }

    @Override
    public List<TbpostDto.DetailResDto> list(TbpostDto.ListReqDto param){
        return detailList(tbpostMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param){
        int[] returnSize = param.init(tbpostMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbpostMapper.pagedList(param)));
    }
    @Override
    public List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListReqDto param){
        param.init();
        return detailList(tbpostMapper.scrollList(param));
    }
    //
    public List<TbpostDto.DetailResDto> detailList(List<TbpostDto.DetailResDto> list){
        List<TbpostDto.DetailResDto> newList = new ArrayList<>();
        for(TbpostDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}

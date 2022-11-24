package com.viyunsion.cefc.modules.app.sales_order.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viyunsion.cefc.modules.app.sales_order.dao.NoteDao;
import com.viyunsion.cefc.modules.app.sales_order.model.Note;
import com.viyunsion.cefc.modules.app.sales_order.service.NoteService;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl extends ServiceImpl<NoteDao, Note> implements NoteService {

    @Override
    public void testMethod() {

    }
}

package com.niyredra.notjustnote.modules.app.note.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niyredra.notjustnote.modules.app.note.dao.NoteDao;
import com.niyredra.notjustnote.modules.app.note.model.Note;
import com.niyredra.notjustnote.modules.app.note.service.NoteService;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl extends ServiceImpl<NoteDao, Note> implements NoteService {

    @Override
    public void testMethod() {

    }
}

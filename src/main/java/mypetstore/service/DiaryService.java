package mypetstore.service;

import mypetstore.domain.Diary;
import mypetstore.persistence.DiaryDAO;
import mypetstore.persistence.impl.DiaryDAOImpl;

public class DiaryService {
    private DiaryDAO diaryDAO;
    public DiaryService(){
        diaryDAO = new DiaryDAOImpl();
    }
    public void insertDiary(Diary diary){
        diaryDAO.insertDiary(diary);
    }

}

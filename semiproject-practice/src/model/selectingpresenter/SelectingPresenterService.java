package model.selectingpresenter;

import java.util.ArrayList;
import java.util.Random;

public class SelectingPresenterService {
	private static SelectingPresenterService service = new SelectingPresenterService();
	
	public static SelectingPresenterService getInstance(){
		return service;
	}
	
	public SelectingPresenterVO selectingPresenter(ArrayList<SelectingPresenterVO> presenterList){
		Random random = new Random();		
		int presenterIndex = random.nextInt(presenterList.size());

		SelectingPresenterVO presenter = presenterList.get(presenterIndex);		
		return presenter;
	}
}

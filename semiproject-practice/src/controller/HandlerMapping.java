package controller;

import controller.inst.board.InstCommentDeleteController;
import controller.inst.board.InstCommentUpdateController;
import controller.inst.board.InstCommentUpdateViewController;
import controller.inst.board.InstCommentWriteController;
import controller.inst.board.InstDeletePostingController;
import controller.inst.board.InstDownloadController;
import controller.inst.board.InstPostingListController;
import controller.inst.board.InstSearchController;
import controller.inst.board.InstShowContentController;
import controller.inst.board.InstShowContentNotHitController;
import controller.inst.board.InstUpdatePostingController;
import controller.inst.board.InstUpdateViewController;
import controller.inst.board.InstWritePostingController;
import controller.proposal.board.ProCommentDeleteController;
import controller.proposal.board.ProCommentUpdateController;
import controller.proposal.board.ProCommentUpdateViewController;
import controller.proposal.board.ProCommentWriteController;
import controller.proposal.board.ProDeletePostingController;
import controller.proposal.board.ProDownloadController;
import controller.proposal.board.ProPostingListController;
import controller.proposal.board.ProSearchController;
import controller.proposal.board.ProShowContentController;
import controller.proposal.board.ProShowContentNoHitController;
import controller.proposal.board.ProUpdatePostingController;
import controller.proposal.board.ProUpdatePostingViewController;
import controller.proposal.board.ProWriteController;
import controller.qna.board.QNACommentDeleteController;
import controller.qna.board.QNACommentUpdateController;
import controller.qna.board.QNACommentUpdateViewController;
import controller.qna.board.QNACommentWriteController;
import controller.qna.board.QNADeletePostingController;
import controller.qna.board.QNADownloadController;
import controller.qna.board.QNAPostingListController;
import controller.qna.board.QNASearchController;
import controller.qna.board.QNAShowContentController;
import controller.qna.board.QNAShowContentNoHitController;
import controller.qna.board.QNAUpdatePostingController;
import controller.qna.board.QNAUpdatePostingViewController;
import controller.qna.board.QNAWriteController;
import controller.selectingGroup.selectingGroupController;
import controller.selectingpresent.SelectingPresenterController;
import controller.selectingpresent.UpdateCntPresentationController;

public class HandlerMapping { // Singleton Pattern으로 구현
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		return instance;
	}

	/**
	 * 클라이언트가 요청한 명령에 해당하는 컨트롤러 구현체 생성
	 * 
	 * @param command
	 * @return
	 */
	public Controller create(String command) {
		Controller controller = null;
		if (command.equals("mainList")) { // 공통 컨트롤러
			controller = new MainController();
		} else if (command.equals("login")) {
			controller = new LoginController();
		} else if (command.equals("logout")) {
			controller = new LogoutController();
		} else if (command.equals("register")) {
			controller = new RegisterController();
		} else if (command.equals("idcheck")) {
			controller = new MemberIdCheckController();
		} else if (command.equals("update")) {
			controller = new UpdateController();
		} else if (command.equals("updateView")) {
			controller = new UpdateViewController();
		} else if (command.equals("delete")) {
			controller = new DeleteController();
		} else if (command.equals("instList")) { // 강사 게시판 컨트롤러
			controller = new InstPostingListController();
		} else if (command.equals("instShowContent")) {
			controller = new InstShowContentController();
		} else if (command.equals("instShowContentNotHit")) {
			controller = new InstShowContentNotHitController();
		} else if (command.equals("instWritePosting")) {
			controller = new InstWritePostingController();
		} else if (command.equals("instDeletePosting")) {
			controller = new InstDeletePostingController();
		} else if (command.equals("instUpdateView")) {
			controller = new InstUpdateViewController();
		} else if (command.equals("instUpdatePosting")) {
			controller = new InstUpdatePostingController();
		} else if (command.equals("instDownload")) {
			controller = new InstDownloadController();
		} else if (command.equals("instSearch")) {
			controller = new InstSearchController();
		} else if (command.equals("InstCommentWrite")) {
			controller = new InstCommentWriteController();
		} else if (command.equals("InstCommentDelete")) {
			controller = new InstCommentDeleteController();
		} else if (command.equals("InstCommentUpdateView")) {
			controller = new InstCommentUpdateViewController();
		} else if (command.equals("InstCommentUpdateAction")) {
			controller = new InstCommentUpdateController();
		} else if (command.equals("proList")) { // 건의사항 게시판 컨트롤러
			controller = new ProPostingListController();
		} else if (command.equals("proShowContent")) {
			controller = new ProShowContentController();
		} else if (command.equals("proShowContentNotHit")) {
			controller = new ProShowContentNoHitController();
		} else if (command.equals("proWrite")) {
			controller = new ProWriteController();
		} else if (command.equals("proDeletePosting")) {
			controller = new ProDeletePostingController();
		} else if (command.equals("proUpdateView")) {
			controller = new ProUpdatePostingViewController();
		} else if (command.equals("proUpdatePosting")) {
			controller = new ProUpdatePostingController();
		} else if (command.equals("proDownload")) {
			controller = new ProDownloadController();
		} else if (command.equals("proSearch")) {
			controller = new ProSearchController();
		} else if (command.equals("proCommentWrite")) {
			controller = new ProCommentWriteController();
		} else if (command.equals("proCommentDelete")) {
			controller = new ProCommentDeleteController();
		} else if (command.equals("proCommentUpdateView")) {
			controller = new ProCommentUpdateViewController();
		} else if (command.equals("proCommentUpdateAction")) {
			controller = new ProCommentUpdateController();
		} else if (command.equals("qnaboardlist")) { // qna 게시판 컨트롤러
			controller = new QNAPostingListController();
		} else if (command.equals("qnawrite")) {
			controller = new QNAWriteController();
		} else if (command.equals("qnashowContentNotHit")) {
			controller = new QNAShowContentNoHitController();
		} else if (command.equals("qnashowContent")) {
			controller = new QNAShowContentController();
		} else if (command.equals("qnadeletePosting")) {
			controller = new QNADeletePostingController();
		} else if (command.equals("qnaupdatePostingView")) {
			controller = new QNAUpdatePostingViewController();
		} else if (command.equals("qnaupdatePosting")) {
			controller = new QNAUpdatePostingController();
		} else if (command.equals("qnaDownload")) {
			controller = new QNADownloadController();
		} else if (command.equals("qnasearch")) {
			controller = new QNASearchController();
		} else if (command.equals("qnaCommentWrite")) {
			controller = new QNACommentWriteController();
		} else if (command.equals("qnaCommentDelete")) {
			controller = new QNACommentDeleteController();
		} else if (command.equals("qnaCommentUpdateView")) {
			controller = new QNACommentUpdateViewController();
		} else if (command.equals("qnaCommentUpdateAction")) {
			controller = new QNACommentUpdateController();
		} else if (command.equals("findid")) {
			controller = new FindIdController();
		} else if (command.equals("selectingPresenter")) { // 발표자 선정
			controller = new SelectingPresenterController();
		} else if (command.equals("updateCntPresentation")) {
			controller = new UpdateCntPresentationController();
		} else if (command.equals("updateCntPresentation")) {
			controller = new UpdateCntPresentationController();
		} else if (command.equals("selectingGroup")) {
			controller = new selectingGroupController();
		}
		return controller;
	}
}
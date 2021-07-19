package io.simpolor.web.controller;

import io.simpolor.web.exception.NotFoundException;
import io.simpolor.web.model.dto.BoardRequest;
import io.simpolor.web.model.dto.BoardResponse;
import io.simpolor.web.model.dto.BoardSearch;
import io.simpolor.web.repository.entity.Board;
import io.simpolor.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/search")
	public ModelAndView search(ModelAndView mav,
							   BoardSearch search,
							   @SortDefault(sort = "seq", direction = Sort.Direction.DESC) Pageable pageable){

		Page page = boardService.search(search.getTitle(), pageable);

		mav.addObject("totalCount", page.getTotalElements());
		mav.addObject("pageable", page.getPageable());
		mav.addObject("boards", BoardResponse.of(page.getContent()));
		mav.setViewName("board_list");
		return mav;
	}

	@RequestMapping(value="/list")
	public ModelAndView list(ModelAndView mav) {

		log.info("/board/list");
		log.debug("/board/list");

		long totalCount = boardService.getTotalCount();
		List<Board> boards = boardService.getAll();

		mav.addObject("totalCount", totalCount);
		mav.addObject("boards", BoardResponse.of(boards));
		mav.setViewName("board_list");
		return mav;
	}

	@RequestMapping(value="/detail/{seq}", method=RequestMethod.GET)
	public ModelAndView detail(ModelAndView mav, @PathVariable Long seq) {

		Board board = boardService.get(seq);

		mav.addObject("board", BoardResponse.of(board));
		mav.setViewName("board_detail");
		return mav;
	}

	@GetMapping(value="/register")
	public ModelAndView registerForm(ModelAndView mav) {

		mav.addObject("board", new BoardResponse());
		mav.setViewName("board_register");
		return mav;
	}

	@PostMapping(value="/register")
	public ModelAndView register(ModelAndView mav,
								 @Valid @ModelAttribute("board") BoardRequest request,
								 BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError error : errors){
				log.debug("Error on object : {}, on field : {}, Message : {}", error.getObjectName(), error.getField(), error.getDefaultMessage());
			}
			mav.setViewName("board_register");
			return mav;
		}

		Board board = request.toObject();
		boardService.create(board);

		mav.setViewName("redirect:/board/detail/"+board.getSeq());
		return mav;
	}

	@GetMapping(value="/modify/{seq}")
	public ModelAndView modifyForm(ModelAndView mav, @PathVariable Long seq) {

		Board board = boardService.get(seq);

		mav.addObject("board", BoardResponse.of(board));
		mav.setViewName("board_modify");
		return mav;
	}

	@PostMapping(value="/modify/{seq}")
	public ModelAndView modify(ModelAndView mav,
							   @PathVariable Long seq,
							   @Valid @ModelAttribute("board") BoardRequest request,
							   BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			mav.setViewName("board_modify");
			return mav;
		}

		request.setSeq(seq);
		Board board = request.toObject();
		boardService.update(board);

		mav.setViewName("redirect:/board/detail/"+seq);
		return mav;
	}

	@PostMapping(value="/delete/{seq}")
	public ModelAndView delete(ModelAndView mav, @PathVariable Long seq) {

		boardService.delete(seq);

		mav.setViewName("redirect:/board/list");
		return mav;
	}

	@GetMapping(value="/comment")
	public ModelAndView comment(ModelAndView mav) {

		throw new NotFoundException();
	}

}

package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.*;
import kr.jadekim.oj.mainserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ohyongtaek on 2016. 2. 24..
 */

@Controller
public class WebBoardController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    QuestionAnswerRepository questionAnswerRepository;

    @RequestMapping("/notice/test")
    public @ResponseBody
    String test(){
        Board b = new Board("notice");
        boardRepository.save(b);
        User user = userRepository.findOne(1);
        Post p = new Post(b,user,new Date(),"title","content");
        postRepository.save(p);

        return "true";
    }


    @RequestMapping("/boardsave/test")
    public @ResponseBody String createBoard(ModelAndView modelAndView){
        Board b = new Board("answers");
        boardRepository.save(b);

        return "true";
    }

    @RequestMapping("/notice")
    public ModelAndView showBoard(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUserInfo");
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Post> post = postRepository.findByBoardId(1,pageable);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        for (Post p : post) {
            Map<String, Object> map = new HashMap<>();
            int num = p.getId();
            String name = p.getTitle();
            String user = p.getAuthor().getName();
            String date = simpleDateFormat.format(p.getTime());
            map.put("number", num);
            map.put("user", user);
            map.put("date", date);
            map.put("title", name);
            messages.add(map);
        }
        if(loginUser!=null) {
            modelAndView.addObject("loginUser", loginUser);
        }
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("board");

        return modelAndView;
    }

    @RequestMapping("/question")
    public ModelAndView showQuestion(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable,HttpSession session) {
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Question> questions = questionRepository.findAll(pageable);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        User loginUser = (User) session.getAttribute("loginUserInfo");
        for (Question p : questions) {
            Map<String, Object> map = new HashMap<>();
            int num = p.getId();
            String name = p.getPost().getTitle();
            String user = p.getPost().getAuthor().getName();
            String date = simpleDateFormat.format(p.getPost().getTime());
            int  quest = p.getProblem().getId();
            map.put("number", num);
            map.put("user", user);
            map.put("date", date);
            map.put("title", name);
            map.put("question", quest);
            if(loginUser!= null && p.getPost().getAuthor().getId() == loginUser.getId()){
                map.put("canModify",true);
                map.put("canDelete",true);
            }
            messages.add(map);
        }
        if(loginUser!=null) {
            modelAndView.addObject("loginUser", loginUser);
        }
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("questionlist");
        return modelAndView;
    }


    @RequestMapping("/notice/{id}")
    public ModelAndView notice(ModelAndView modelAndView, @PathVariable("id")int id,HttpSession session) {
        Post notice = postRepository.findOne(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Map<String, Object> map = new HashMap<>();
        User loginUser = (User) session.getAttribute("loginUserInfo");
        int num = id;
        String title = notice.getTitle();
        String user = notice.getAuthor().getName();
        String date = simpleDateFormat.format(notice.getTime());
        String contents = notice.getContent();
        map.put("id", notice.getId());
        map.put("number", num);
        map.put("user", user);
        map.put("date", date);
        map.put("title", title);
        map.put("contents", contents);
        if(loginUser!=null) {
            modelAndView.addObject("loginUser", loginUser);
        }
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("notice");
        return modelAndView;
    }
    @RequestMapping("/question/{id}")
    public ModelAndView showQuestion(ModelAndView modelAndView, @PathVariable("id")int id,HttpSession session){
        Question question = questionRepository.findOne(id);
        if(question==null){
            modelAndView.setViewName("redirect:problem/"+id);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Map<String, Object> map = new HashMap<>();
        User loginUser = (User) session.getAttribute("loginUserInfo");
        Post post = question.getPost();
        List<QuestionAnswer> answers = question.getAnswers();
        Problem problem = question.getProblem();
        String title = post.getTitle();
        String user = post.getAuthor().getName();
        String date = simpleDateFormat.format(post.getTime());
        String contents = post.getContent();
        map.put("id", question.getId());
        map.put("title", title);
        map.put("question_user", user);
        map.put("question_date", date);
        map.put("question_contents", contents);
        map.put("problem_id", problem.getId());
        map.put("problem_name", problem.getName());
        if(loginUser!= null && question.getPost().getAuthor().getId()==loginUser.getId()){
            map.put("canModifyAndDelete",true);
        }
        ArrayList<Map> answerMessages = new ArrayList<>();
        if(!answers.isEmpty()){
            String answer_title, answer_date, answer_user, answer_contents;
            int answer_id;
            System.out.println(answers.get(0).getPost().getContent());
            for(QuestionAnswer ans : answers){
                Map<String, Object> answermap = new HashMap<>();
                Post answerPost = ans.getPost();
                answer_title = answerPost.getTitle();
                answer_date = simpleDateFormat.format(answerPost.getTime());
                answer_user = answerPost.getAuthor().getName();
                answer_contents = answerPost.getContent();
                answer_id = ans.getId();
                answermap.put("answer_id", answer_id);
                answermap.put("answer_title", answer_title);
                answermap.put("answer_date", answer_date);
                answermap.put("answer_user", answer_user);
                answermap.put("answer_contents", answer_contents);
                if(loginUser!=null && ans.getPost().getAuthor().getId()!=loginUser.getId()){
                    answermap.put("canModifyAndDelete",true);
                }
                answerMessages.add(answermap);
            }
        }
        if(loginUser!=null) {
            modelAndView.addObject("loginUser", loginUser);
        }
        modelAndView.addObject("messages", map);
        modelAndView.addObject("answers", answerMessages);
        modelAndView.setViewName("questionAnswer");
        return modelAndView;
    }

    @RequestMapping(value = "/question/write", method = RequestMethod.GET)
    public ModelAndView writeQuestion(ModelAndView modelAndView, HttpSession session){
        User user = (User) session.getAttribute("loginUserInfo");
        if(user == null){
            modelAndView.setViewName("redirect:/question");
            return modelAndView;
        }
        modelAndView.addObject("loginUser",user);
        modelAndView.setViewName("questionWrite");
        return modelAndView;
    }

    @RequestMapping(value = "/question/write" ,method = RequestMethod.POST)
    public ModelAndView saveQuestion(HttpServletRequest request, ModelAndView modelAndView,HttpSession session){
        String title = request.getParameter("question_title");
        int question_problem = Integer.valueOf(request.getParameter("question_problem"));
        String content = request.getParameter("question_contents");
        Board board = boardRepository.findOne(2);
        User user = (User) session.getAttribute("loginUserInfo");
        if(user == null){
            modelAndView.setViewName("redirect:/question");
            return modelAndView;
        }
        Date date = new Date();
        Post post = new Post(board, user, date, title, content);
        postRepository.save(post);
        Problem problem = problemRepository.findOne(question_problem);
        Question question = new Question(post, problem);
        questionRepository.save(question);
        modelAndView.setViewName("redirect:/question");
        return modelAndView;
    }

    @RequestMapping(value = "/question/answerwrite/{id}", method = RequestMethod.GET)
    public ModelAndView writeAnswer(ModelAndView modelAndView, @PathVariable("id")int id,HttpSession session){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        User loginUser = (User) session.getAttribute("loginUserInfo");
        if(loginUser!=null) {
            modelAndView.addObject("loginUser", loginUser);
        }
        modelAndView.addObject("messages",map);
        modelAndView.setViewName("answerWrite");
        return modelAndView;
    }

    @RequestMapping(value = "/question/answerwrite/{id}", method = RequestMethod.POST)
    public ModelAndView saveAnswer(HttpServletRequest request, ModelAndView modelAndView, @PathVariable("id")int id){
        String title = request.getParameter("answer_title");
        String answer_contents = request.getParameter("answer_contents");
        Board board = boardRepository.findOne(3);
        User user = userRepository.findOne(1);
        Date date = new Date();
        Post post = new Post(board, user, date, title, answer_contents);
        postRepository.save(post);
        Question question = questionRepository.findOne(id);
        QuestionAnswer questionAnswer = new QuestionAnswer(question, post);
        questionAnswerRepository.save(questionAnswer);
        question.getAnswers().add(questionAnswer);
        questionRepository.save(question);

        modelAndView.setViewName("redirect:/question/{id}");
        return modelAndView;
    }

    @RequestMapping(value = "/question/delete/{id}")
    public ModelAndView deleteQuestion(ModelAndView modelAndView, @PathVariable("id")int id,HttpSession session){
        Question question = questionRepository.findOne(id);
        User loginUser = (User) session.getAttribute("loginUserInfo");
        if(loginUser==null || question.getPost().getAuthor().getId()!=loginUser.getId()){
            modelAndView.setViewName("redirect:/question");
            return modelAndView;
        }
        Post post = question.getPost();
        List<QuestionAnswer> questionAnswers = question.getAnswers();
        for(QuestionAnswer questionAnswer : questionAnswers){
            questionAnswerRepository.delete(questionAnswer);
            postRepository.delete(questionAnswer.getPost());

        }
        questionRepository.delete(question);
        postRepository.delete(post);
        modelAndView.setViewName("redirect:/question");
        return modelAndView;
    }

    @RequestMapping(value = "/question/modify/{id}", method = RequestMethod.GET)
    public ModelAndView showm_modifyQuestion(ModelAndView modelAndView, @PathVariable("id")int id,HttpSession session){
        Map<String, Object> map = new HashMap<>();
        Question question = questionRepository.findOne(id);
        User loginUser = (User) session.getAttribute("loginUserInfo");
        Post post = question.getPost();
        String title = post.getTitle();
        String contents = post.getContent();
        int problem_id = question.getProblem().getId();
        map.put("id", id);
        map.put("title", title);
        map.put("contents", contents);
        map.put("problem_id", problem_id);
        if(loginUser!=null){
            modelAndView.addObject("loginUser",loginUser);
        }
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("questionModify");

        return modelAndView;
    }

    @RequestMapping(value = "/question/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyQuestion(HttpServletRequest request, ModelAndView modelAndView, @PathVariable("id")int id){
        String title = request.getParameter("question_title");
        int question_problem = Integer.valueOf(request.getParameter("question_problem"));
        Problem problem = problemRepository.findOne(question_problem);
        String content = request.getParameter("question_contents");
        Question question = questionRepository.findOne(id);
        Post post = question.getPost();
        post.setTitle(title);
        post.setContent(content);
        Date date = new Date();
        post.setTime(date);
        postRepository.save(post);
        question.setProblem(problem);
        questionRepository.save(question);
        modelAndView.setViewName("redirect:/question/{id}");
        return modelAndView;
    }

    @RequestMapping(value = "/question/answermodify/{id}", method = RequestMethod.GET)
    public ModelAndView showModifyAnswerq(ModelAndView modelAndView, @PathVariable("id")int id){
        QuestionAnswer questionAnswer = questionAnswerRepository.findOne(id);
        int question_id = questionAnswer.getQuestion().getId();
        Map<String, Object> map = new HashMap<>();
        Post post = questionAnswer.getPost();
        String title = post.getTitle();
        String contents = post.getContent();
        map.put("title", title);
        map.put("contents", contents);
        map.put("question_id", question_id);
        map.put("id", id);
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("answerModify");
        return modelAndView;
    }

    @RequestMapping(value = "/question/answermodify/{id}", method = RequestMethod.POST)
    public ModelAndView ModifyAnswer(HttpServletRequest request, ModelAndView modelAndView, @PathVariable("id")int id){
        String title = request.getParameter("answer_title");
        String contents = request.getParameter("answer_contents");
        QuestionAnswer questionAnswer = questionAnswerRepository.findOne(id);
        int question_id = questionAnswer.getQuestion().getId();
        Post post = questionAnswer.getPost();
        post.setTitle(title);
        post.setContent(contents);
        postRepository.save(post);
        questionAnswerRepository.save(questionAnswer);
        modelAndView.setViewName("redirect:/question/"+question_id);
        return modelAndView;
    }

    @RequestMapping("/question/answerdelete/{id}")
    public ModelAndView deleteAnswer(ModelAndView modelAndView, @PathVariable("id")int id){
        QuestionAnswer questionAnswer = questionAnswerRepository.findOne(id);
        int question_id = questionAnswer.getQuestion().getId();
        Post post = questionAnswer.getPost();
        questionAnswerRepository.delete(questionAnswer);
        postRepository.delete(post);
        modelAndView.setViewName("redirect:/question/"+question_id);
        return modelAndView;
    }

    @RequestMapping(value = "/notice/write", method = RequestMethod.GET)
    public ModelAndView showwriteNotice(ModelAndView modelAndView){
        modelAndView.setViewName("noticewrite");
        return modelAndView;
    }

    @RequestMapping(value = "/notice/write", method = RequestMethod.POST)
    public ModelAndView writeNotice(HttpServletRequest request, ModelAndView modelAndView, HttpSession session){
        String title = request.getParameter("notice_title");
        String contents = request.getParameter("notice_contents");
        Board board = boardRepository.findOne(1);
        User loginUser = (User) session.getAttribute("loginUserInfo");
        modelAndView.setViewName("redirect:/notice");
        if(loginUser == null){
            return modelAndView;
        }else{
            //System.out.println(loginUser.getLoginId());
        }
        Date date = new Date();
        Post post = new Post(board, loginUser, date, title, contents);
        postRepository.save(post);
        return modelAndView;
    }

    @RequestMapping(value = "notice/modify/{id}", method = RequestMethod.GET)
    public ModelAndView shownoticemodify(ModelAndView modelAndView, @PathVariable("id")int id){
        Map<String, Object> map = new HashMap<>();
        Post post = postRepository.findOne(id);
        map.put("id", id);
        map.put("title", post.getTitle());
        map.put("contents", post.getContent());
        System.out.println(post.getTitle());
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("noticemodify");
        return modelAndView;
    }

    @RequestMapping(value = "notice/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyNotice(HttpServletRequest request, ModelAndView modelAndView, HttpSession session, @PathVariable("id")int id){
        String title = request.getParameter("notice_title");
        String contents = request.getParameter("notice_contents");
        Post post = postRepository.findOne(id);
        post.setContent(contents);
        post.setTitle(title);
        postRepository.save(post);
        modelAndView.setViewName("redirect:/notice");
        return modelAndView;
    }

    @RequestMapping("notice/delete/{id}")
    public ModelAndView deleteNotice(ModelAndView modelAndView, @PathVariable("id")int id){
        Post post = postRepository.findOne(id);
        System.out.println(post.getContent());
        postRepository.delete(post);
        modelAndView.setViewName("redirect:/notice");
        return modelAndView;
    }
}

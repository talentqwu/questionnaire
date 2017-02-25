package com.kam.qs.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.web.DoradoContext;
import com.kam.qs.dao.exampool.AnswerDao;
import com.kam.qs.dao.exampool.InstanceDao;
import com.kam.qs.dao.exampool.QuestionDao;
import com.kam.qs.dao.exampool.TemplateDao;
import com.kam.qs.emnu.QuestionShowType;
import com.kam.qs.emnu.QuestionType;
import com.kam.qs.entity.exampool.Answer;
import com.kam.qs.entity.exampool.Instance;
import com.kam.qs.entity.exampool.Question;
import com.kam.qs.entity.exampool.Template;

@Component(value = "qs.exampoolService")
public class ExampoolService {

	private static final org.apache.commons.logging.Log logger = LogFactory.getLog(ExampoolService.class);
	
	@Resource
	private TemplateDao templateDao;
	
	@Resource
	private QuestionDao questionDao;
	
	@Resource
	private InstanceDao instanceDao;
	
	@Resource
	private AnswerDao answerDao;
	
	@DataProvider
	public List<Instance> getInstanceByTemplateId(String templateId) throws Exception {
		List<Instance> results = new ArrayList<Instance>();
		
		List<Object[]> datas = instanceDao.getByTemplate(templateId);
		for (Object[] data : datas) {
			Instance entity = EntityUtils.toEntity(data[0]);
			EntityUtils.setValue(entity, "question", data[1]);
			results.add(entity);
		}
		
		return results;
	}
	
	@DataProvider
	public List<Question> getQuestionRoot() throws Exception {
		List<Question> results = new ArrayList<Question>();
		
		List<Object[]> datas = questionDao.getRoot();
		for (Object[] data : datas) {
			Question entity = EntityUtils.toEntity(data[0]);
			EntityUtils.setValue(entity, "hasChild", ((long)data[1] > 0 ? true : false));
			results.add(entity);
		}
		
		return results;
	}
	
	@DataProvider
	public List<Question> getQuestionChildren(String parentId) throws Exception {
		List<Question> results = new ArrayList<Question>();
		
		List<Object[]> datas = questionDao.getChildrenByParentId(parentId);
		for (Object[] data : datas) {
			Question entity = EntityUtils.toEntity(data[0]);
			EntityUtils.setValue(entity, "parentId", data[1]);
			EntityUtils.setValue(entity, "hasChild", ((long)data[2] > 0 ? true : false));
			results.add(entity);
		}
		return results;
	}
	
	@DataProvider
	public List<Answer> getAnswerByQuestionId(String questionId) {
		return answerDao.getByQuestion(questionId);
	}
	
	@DataProvider
	public List<Template> getAllTemplate() {
		return templateDao.getAll();
	}
	
	@Transactional
	public void initQuestion() throws IOException {
		if (questionDao.getAll().size() > 0) return;
		
		logger.info("初始系统默认试题资料... ...");
		Question parent = null, question = null;
		String root = DoradoContext.getCurrent().getServletContext().getRealPath("/"), line;
		String filePath = root + "data" + File.separatorChar + "question.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
		while((line = br.readLine()) != null) {
			if (StringUtils.isBlank(line) || line.startsWith("#")) {
				parent = null;
				question = null;
				continue;
			}
			
			String content = line.substring(1);
			if (line.startsWith("~")) {
				String[] data = content.split(":");
				Answer answer = new Answer(Integer.valueOf(data[0]), data[1], Double.parseDouble(data[2]));
				if (data.length == 4)
					answer.setAdditional(true);
				answer.setQuestion(question);
				answerDao.save(answer);
			} else {
				String[] datas = content.split(":");
				QuestionType type = QuestionType.valueOf(datas[1]);
				QuestionShowType showType = QuestionShowType.valueOf(datas[2]);
				question = new Question(datas[0], type, showType);
				question.setParent(parent);
				if (type.equals(QuestionType.IS_PARENT))
					parent = question;
				questionDao.save(question);
			}
		}
		br.close();
		logger.info("所有的默认试题资料都已经初始化。");
	}
	
	@Transactional
	public void initTemplate() throws IOException{
		String root = DoradoContext.getCurrent().getServletContext().getRealPath("/"), line;
		
		Template template = null;
		if (templateDao.getAll().size() == 0) {
			logger.info("初始默认的问卷模板资料... ...");
			template = new Template();
			
			String filePath = root + "data" + File.separatorChar + "template.txt";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
			while((line = br.readLine()) != null) {
				if (line.startsWith("name:"))
					template.setName(line.substring("name:".length()));
				else if (line.startsWith("title:"))
					template.setTitle(line.substring("title:".length()));
				else if (line.startsWith("description:"))
					template.setDescription(line.substring("description:".length()));
				else if (line.startsWith("image:"))
					template.setImageUrl(line.substring("image:".length()));
			}
			templateDao.save(template);
			br.close();
			logger.info("默认的问卷模板都已经初始化。");
		} else
			template = templateDao.getAll().get(0);
		
		if (instanceDao.getAll().size() == 0) {
			Instance parent = null, instance = null;
			String filePath = root + "data" + File.separatorChar + "instance.txt";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
			while((line = br.readLine()) != null) {
				if (StringUtils.isBlank(line) || line.startsWith("#")) continue;
				
				String[] datas = line.split(",");
				int order = Integer.parseInt(datas[0]);
				Question question = questionDao.get(datas[1]);
				QuestionType questionType = QuestionType.valueOf(datas[2]);
				String parentQuestionId = datas[3];
				double proportion = Double.parseDouble(datas[4]);
				instance = new Instance(proportion, question, template);
				instance.setOrder(order);
				if (!parentQuestionId.equals("NULL")) 
					instance.setParent(parent);
				if (questionType.equals(QuestionType.IS_PARENT)) 
					parent = instance;
				instanceDao.save(instance);
			}
			br.close();
		}
	}
}

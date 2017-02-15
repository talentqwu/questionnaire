package com.kam.qs.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.entity.FilterType;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.web.DoradoContext;
import com.kam.qs.dao.common.ArchivesDao;
import com.kam.qs.dao.common.DoradoServiceDao;
import com.kam.qs.dao.common.IndustryDao;
import com.kam.qs.dao.common.LiaisonsDao;
import com.kam.qs.dao.common.LogDao;
import com.kam.qs.dao.common.RegionDao;
import com.kam.qs.dao.common.TreeNodeDao;
import com.kam.qs.emnu.ArchivesType;
import com.kam.qs.emnu.Role;
import com.kam.qs.emnu.TreeNodeCategory;
import com.kam.qs.entity.common.Archives;
import com.kam.qs.entity.common.DoradoService;
import com.kam.qs.entity.common.Industry;
import com.kam.qs.entity.common.Liaisons;
import com.kam.qs.entity.common.Log;
import com.kam.qs.entity.common.Permission;
import com.kam.qs.entity.common.Region;
import com.kam.qs.entity.common.TreeNode;
import com.kam.qs.entity.common.User;
import com.kam.qs.pojo.KeyValue;
import com.kam.qs.util.Constants;

@Component(value = "qs.commonService")
public class CommonService {

	private static final org.apache.commons.logging.Log logger = LogFactory.getLog(CommonService.class);
	
	@Resource
	private ArchivesDao archivesDao;
	
	@Resource
	private TreeNodeDao treeNodeDao;
	
	@Resource
	private DoradoServiceDao doradoServiceDao;
	
	@Resource
	private LogDao logDao;
	
	@Resource
	private IndustryDao industryDao;
	
	@Resource
	private RegionDao regionDao;
	
	@Resource
	private LiaisonsDao liaisonsDao;
	
	@DataProvider
	public void queryLiaisons(Page<Liaisons> page, Map<String, Object> parameter) {
		liaisonsDao.getByParameter(page, parameter);
	}
	
	@DataResolver
	@Transactional
	public void saveLiaisons(Collection<Liaisons> datas) throws Exception {
		for (Iterator<Liaisons> iter = EntityUtils.getIterator(datas,
				FilterType.DELETED, Liaisons.class); iter.hasNext();) {
			 liaisonsDao.delete(iter.next());
		}
		for (Iterator<Liaisons> iter = EntityUtils.getIterator(datas,
				FilterType.MODIFIED, Liaisons.class); iter.hasNext();) {
			liaisonsDao.save(iter.next());
		}
		for (Iterator<Liaisons> iter = EntityUtils.getIterator(datas,
				FilterType.NEW, Liaisons.class); iter.hasNext();) {
			 liaisonsDao.save(iter.next());
		}
	}
	
	@DataProvider
	public List<Region> getAllRegion() {
		return regionDao.getAll();
	}
	
	@DataResolver
	@Transactional
	public void saveRegion(Collection<Region> datas) throws Exception {
		for (Iterator<Region> iter = EntityUtils.getIterator(datas,
				FilterType.DELETED, Region.class); iter.hasNext();) {
			regionDao.delete(iter.next());
		}
		for (Iterator<Region> iter = EntityUtils.getIterator(datas,
				FilterType.MODIFIED, Region.class); iter.hasNext();) {
			regionDao.save(iter.next());
		}
		for (Iterator<Region> iter = EntityUtils.getIterator(datas,
				FilterType.NEW, Region.class); iter.hasNext();) {
			regionDao.save(iter.next());
		}
	}
	
	@DataProvider
	public List<Industry> getAllIndustry() {
		return industryDao.getAll();
	}
	
	@DataResolver
	@Transactional
	public void saveIndustry(Collection<Industry> datas) throws Exception {
		for (Iterator<Industry> iter = EntityUtils.getIterator(datas,
				FilterType.DELETED, Industry.class); iter.hasNext();) {
			industryDao.delete(iter.next());
		}
		for (Iterator<Industry> iter = EntityUtils.getIterator(datas,
				FilterType.MODIFIED, Industry.class); iter.hasNext();) {
			industryDao.save(iter.next());
		}
		for (Iterator<Industry> iter = EntityUtils.getIterator(datas,
				FilterType.NEW, Industry.class); iter.hasNext();) {
			industryDao.save(iter.next());
		}
	}
	
	@DataProvider
	public List<DoradoService> getDoradoServices() {
		return doradoServiceDao.getAll();
	}
	
	@DataResolver
	@Transactional
	public void saveDoradoServices(List<DoradoService> datas) {
		doradoServiceDao.persistEntities(datas);
	}
	
	@DataProvider
	public List<Log> getLogByEntityId(String entityId) {
		return logDao.getByEntityId(entityId);
	}
	
	@DataProvider
	public List<TreeNode> getTreeRootRestrictPermission(String category) throws Exception {
		List<Object[]> results = treeNodeDao.getRootByCategory(TreeNodeCategory.valueOf(category));
		return addPropertiesToTreeNodeRestrictPermission(results);
	}
	
	@DataProvider
	public List<TreeNode> getTreeChildrenRestrictPermission(String parentId) throws Exception {
		List<Object[]> results = treeNodeDao.getChildrenByParentId(parentId);
		return addPropertiesToTreeNodeRestrictPermission(results);
	}
	
	@DataProvider
	public List<TreeNode> getTreeRoot(String category) throws Exception {
		List<Object[]> results = treeNodeDao.getRootByCategory(TreeNodeCategory.valueOf(category));
		return addPropertiesToTreeNode(results);
	}
	
	@DataProvider
	public List<TreeNode> getTreeChildren(String parentId) throws Exception {
		List<Object[]> results = treeNodeDao.getChildrenByParentId(parentId);
		return addPropertiesToTreeNode(results);
	}
	
	@DataProvider
	@SuppressWarnings("unchecked")
	public List<KeyValue> getEnumKeyValues(String enumClassName) throws Exception {
		List<KeyValue> results = new ArrayList<KeyValue>();
		
		String enumClassFullName = Role.class.getPackage().getName() + "." + enumClassName;
		Class<?> enumClass = Class.forName(enumClassFullName);
		Map<String, String> map = (Map<String, String>)enumClass.getMethod("toMap").invoke(null);
		for (String key : map.keySet())
			results.add(new KeyValue(key, map.get(key)));
		
		return results;
	}
	
	@DataProvider
	@SuppressWarnings("unchecked")
	public Map<String, String> getArchivesMapValues(String typeName) throws Exception {
		if (typeName == null || typeName.length() == 0)
			throw new Exception("getArchiveMapValues参数category不能为空！");
		
		ArchivesType type = ArchivesType.valueOf(typeName);
		logger.debug("====> qs.commonService#getArchivesMapValues." + typeName);
		
		DoradoContext context = DoradoContext.getCurrent();
		Map<String, String> results = (Map<String, String>)context.getAttribute(
				DoradoContext.APPLICATION, typeName);
		if (results == null) {
			results = new LinkedHashMap<String, String>();
			List<Archives> archiveses = archivesDao.getByType(type);
			for (Archives archives : archiveses)
				results.put(archives.getCode(), archives.getContent());
			
			context.setAttribute(DoradoContext.APPLICATION, typeName, results);
			// 保存键值
			List<String> mapValueKeys = (List<String>)context.getAttribute(
					DoradoContext.APPLICATION, Constants.CACHED_MAP_VALUE_KEY_NAME);
			if (mapValueKeys == null)
				mapValueKeys = new ArrayList<String>();
			mapValueKeys.add(typeName);
			context.setAttribute(DoradoContext.APPLICATION, 
					Constants.CACHED_MAP_VALUE_KEY_NAME, mapValueKeys);
			
			logger.debug("将MapValue放入到系统的缓存中：" + typeName);
		}
		
		return results;
	}
	
	@DataResolver
	@Transactional
	public void updateTreeNodeSignle(List<TreeNode> treeNodes) {
		treeNodeDao.persistEntities(treeNodes);
	}
	
	@Expose
	@Transactional
	public void initTreeNode() throws IOException {
		if (treeNodeDao.getAll().size() > 0) return;
		
		int count = 0;
		TreeNode parent = null, current = null;
		List<TreeNode> parents = null;
		
		TreeNodeCategory category = null;
		String root = DoradoContext.getCurrent().getServletContext().getRealPath("/"), line;
		String filePath = root + "data" + File.separatorChar + "treenode.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
		while((line = br.readLine()) != null){
			if (line.startsWith("-")) {
				count = 0;
				category = TreeNodeCategory.valueOf(line.split("\\|")[1]);
				logger.info("初始化树形菜单（" + category.getDescription() + "）... ...");
			} else {
				StringTokenizer st = new StringTokenizer(line, ",", false);
				String parentLabel = null, currentLabel;
				while (st.hasMoreTokens()) {
					currentLabel = st.nextToken();
					if (treeNodeDao.getCountByLabel(category, currentLabel) == 0) {
						parents = treeNodeDao.getByLabel(category, parentLabel);
						parent = parents.size() > 0 ? parents.get(0) : null;
						current = new TreeNode();
						current.setCategory(category);
						current.setLabel(currentLabel);
						current.setOrder(count++);
						current.setParent(parent);
						treeNodeDao.save(current);
					}
					parentLabel = currentLabel;
				}
			}
		}
		br.close();
		logger.info("所有的树形菜单都已经初始化。");
	}
	
	@Expose
	@Transactional
	public void initArchives() throws IOException {
		if (archivesDao.getAll().size() > 0) return;
		
		int count = 0;
		ArchivesType archivesType = null;
		String root = DoradoContext.getCurrent().getServletContext().getRealPath("/"), line;
		String filePath = root + "data" + File.separatorChar + "archives.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
		while((line = br.readLine()) != null) {
			if (line.startsWith("-")) {
				count = 0;
				archivesType = ArchivesType.valueOf(line.split("\\|")[1]);
				logger.info("初始系统设置档案（" + archivesType.getDescription() + "）... ...");
			} else {
				String[] datas = line.split(",");
				// code,content,type,order
				Archives archives = new Archives(datas[0], datas[1], archivesType, count++);
				archivesDao.save(archives);
			}
		}
		br.close();
		logger.info("所有的系统设置档案都已经初始化。");
	}
	
	@Expose
	@Transactional
	public void initIndustries() throws IOException {
		if (industryDao.getAll().size() > 0) return;
		
		logger.info("初始GB/4754-2011行业分类资料... ...");
		String root = DoradoContext.getCurrent().getServletContext().getRealPath("/"), line;
		String filePath = root + "data" + File.separatorChar + "industry.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
		while((line = br.readLine()) != null) {
			String[] datas = line.split(",");
			// code,name
			industryDao.save(new Industry(datas[0], datas[1]));
		}
		br.close();
		logger.info("所有的行业分类资料都已经初始化。");
	}
	
	private List<TreeNode> addPropertiesToTreeNode(List<Object[]> results) throws Exception {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		
		for (Object[] result : results) {
			TreeNode treeNode = EntityUtils.toEntity((TreeNode) result[0]);
			int childCount = Integer.parseInt(result[1].toString());
			EntityUtils.setValue(treeNode, "childCount", childCount);
			EntityUtils.setValue(treeNode, "hasChild", childCount > 0);
			EntityUtils.setValue(treeNode, "checked", false);
			treeNodes.add(treeNode);
		}
		
		return treeNodes;
	}
	
	private List<TreeNode> addPropertiesToTreeNodeRestrictPermission(List<Object[]> results) throws Exception {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		
		User currentUser = (User)DoradoContext.getCurrent().getAttribute(
				DoradoContext.SESSION, Constants.CURRENT_USER);
		for (Object[] result : results) {
			TreeNode treeNode = EntityUtils.toEntity((TreeNode) result[0]);
			List<Role> roles = treeNode.getRoles();
			boolean pass = false;
			if (roles != null && roles.size() > 0)
				for (Role role : roles) {
					for (Permission permission : currentUser.getPermissions()) {
						if (role.equals(permission.getRole())) {
							pass = true;
							break;
						}
					}
					if (pass) break;
				}
			else
				pass = true;
			if (pass) {
				int childCount = Integer.parseInt(result[1].toString());
				EntityUtils.setValue(treeNode, "childCount", childCount);
				EntityUtils.setValue(treeNode, "hasChild", childCount > 0);
				EntityUtils.setValue(treeNode, "checked", false);
				treeNodes.add(treeNode);
			}
		}
		
		return treeNodes;
	}
}

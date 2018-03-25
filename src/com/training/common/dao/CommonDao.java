package com.training.common.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;

import com.training.page.Pagination;
import com.training.page.SearchResult;

public class CommonDao {

	private SessionFactory sessionFactory;

	private JdbcTemplate jdbcTemplate;


	public <T> void saveOrUpdateEntity(T entry) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(entry);
	}

	public <T> void refresh(T entry) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.refresh(entry);
	}

	public <T> void update(T entry) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(entry);
	}

	public <T> void merge(T entry) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.merge(entry);
	}

	public <T> void delete(T entry) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(entry);
	}

	public <T> void deleteAll(Collection<T> entities) {
		Session session = this.getSessionFactory().getCurrentSession();
		for (Iterator<T> it = entities.iterator(); it.hasNext();) {
			session.delete(it.next());
		}
	}

	public <T> void saveOrUpdateAllEntity(Collection<T> entities) {
		Session session = this.getSessionFactory().getCurrentSession();
		for (Iterator<T> it = entities.iterator(); it.hasNext();) {
			session.saveOrUpdate(it.next());
		}
	}

	public <T> List<T> loadAllEntities(Class<T> clazz) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(clazz);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// 消除掉重复实体对象
		return criteria.list();
	}

	// 单个属性查询对象list
	public <T> List<T> getEntitiesByField(Class<T> clazz, String field, Object value) {
		return getEntitiesByField(clazz, field, value, null);
	}

	public <T> List<T> getEntitiesByField(Class<T> clazz, String field, Object value, HashMap<String, String> orders) {
		// 加限制的
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(clazz)
				.add(Restrictions.eq(field, value));
		if (orders != null && !orders.isEmpty()) {
			Iterator it = orders.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry) it.next();
				if (entry.getKey().toString().equalsIgnoreCase("ASC")) {

					criteria.addOrder(Order.asc(entry.getValue()));
					System.out.println(entry.getKey() + "\t" + entry.getValue());
				} else if (entry.getKey().toString().equalsIgnoreCase("DESC")) {
					criteria.addOrder(Order.desc(entry.getValue()));
				}
			}
		}
		return criteria.list();
	}

	public <T> SearchResult<T> getEntitiesByFieldWithPagination(Class<T> clazz, String field, Object value,
			HashMap<String, String> orders, Pagination page) {
		// 加限制的
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(clazz)
				.add(Restrictions.eq(field, value));
		List tempList = criteria.list();
		page.setTotalCount(tempList.size());
		int totalTemp = tempList.size() % page.getPageSize();
		int totalTemp2 = tempList.size() / page.getPageSize();
		page.setTotalPage(totalTemp == 0 ? totalTemp2 : (totalTemp2 + 1));

		criteria.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
		criteria.setMaxResults(page.getPageSize());

		if (orders != null && !orders.isEmpty()) {
			Iterator it = orders.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry) it.next();
				if (entry.getKey().toString().equalsIgnoreCase("ASC")) {

					criteria.addOrder(Order.asc(entry.getValue()));
				} else if (entry.getKey().toString().equalsIgnoreCase("DESC")) {
					criteria.addOrder(Order.desc(entry.getValue()));
				}
			}
		}

		List list = criteria.list();

		SearchResult<T> searchResult = new SearchResult<T>();
		searchResult.setPagination(page);
		searchResult.setResult(list);
		return searchResult;
	}

	// 多个属性查询对象list
	// public <T> SearchResult<T> getEntitiesByFields(Class<T> clazz,
	// Map<String, Object> fields) {
	// return getEntitiesByFields(clazz, fields, null, null);
	// }

	public <T> SearchResult<T> getEntitiesByFields(Class<T> clazz, Map<String, Object> fields,
			HashMap<String, String> orders, Pagination page) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(clazz);

		if (fields != null && !fields.isEmpty()) {
			Iterator it = fields.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				criteria.add(Restrictions.eq((String) entry.getKey(), entry.getValue()));
			}
		}

		List tempList = criteria.list();
		page.setTotalCount(tempList.size());
		int totalTemp = tempList.size() % page.getPageSize();
		int totalTemp2 = tempList.size() / page.getPageSize();
		page.setTotalPage(totalTemp == 0 ? totalTemp2 : (totalTemp2 + 1));

		criteria.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
		criteria.setMaxResults(page.getPageSize());

		if (orders != null && !orders.isEmpty()) {
			Iterator it = orders.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry) it.next();
				if (entry.getKey().toString().equalsIgnoreCase("ASC")) {
					criteria.addOrder(Order.asc(entry.getValue()));
				} else if (entry.getKey().toString().equalsIgnoreCase("DESC")) {
					criteria.addOrder(Order.desc(entry.getValue()));
				}
			}
		}
		List list = criteria.list();
		SearchResult<T> searchResult = new SearchResult<T>();
		searchResult.setPagination(page);
		searchResult.setResult(list);
		return searchResult;
	}

	public <T> List<T> getEntitiesByFields(Class<T> clazz, Map<String, Object> fields) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(clazz);

		if (fields != null && !fields.isEmpty()) {
			Iterator it = fields.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				criteria.add(Restrictions.eq((String) entry.getKey(), entry.getValue()));
			}
		}
		List list = criteria.list();
		return list;

	}

	// pagination data
	public <T> SearchResult<T> search(String sql, Pagination page) {
		return search(sql, page, null);
	}

	// pagination data
	public <T> SearchResult<T> search(String sql, Pagination page, Map<String, Object> map) {
		Session session = this.sessionFactory.getCurrentSession();//
		Query query = session.createQuery(sql);
		query.setProperties(map);
		List tempList = query.list();
		page.setTotalCount(tempList.size());
		int totalTemp = tempList.size() % page.getPageSize();
		int totalTemp2 = tempList.size() / page.getPageSize();
		page.setTotalPage(totalTemp == 0 ? totalTemp2 : (totalTemp2 + 1));
		query.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		SearchResult<T> searchResult = new SearchResult<T>();
		List<T> result = query.list();
		searchResult.setPagination(page);
		searchResult.setResult(result);
		return searchResult;
	}

	// pagination data
	public <T> SearchResult<T> search(String sql, Pagination page, Map<String, Object> params, Class clazz) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql).addEntity(clazz);
		if (params != null && !params.isEmpty()) {
			query.setProperties(params);
		}
		List tempList = query.list();
		page.setTotalCount(tempList.size());
		int totalTemp = tempList.size() % page.getPageSize();
		int totalTemp2 = tempList.size() / page.getPageSize();
		page.setTotalPage(totalTemp == 0 ? totalTemp2 : (totalTemp2 + 1));
		query.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		SearchResult<T> searchResult = new SearchResult<T>();
		List<T> result = query.list();
		searchResult.setPagination(page);
		searchResult.setResult(result);
		return searchResult;
	}

	// 根据ID查询 单个 对象
	public <T> Object load(Class<T> clazz, int id) {
		return this.getSessionFactory().getCurrentSession().load(clazz, id);
	}

	// 根据ID查询 单个 对象
	public <T> Object get(Class<T> clazz, int id) {
		return this.getSessionFactory().getCurrentSession().get(clazz, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

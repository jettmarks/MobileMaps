/**
 *   Copyright 2010 Jett Marks
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Generated ${date} by Hibernate Tools ${version}
 */
${pojo.getPackageDeclaration()}

<#assign classbody>
import org.hibernate.Session;
import com.jettmarks.db.HibernateUtil;
import com.jettmarks.db.BaseDAO;
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Data Access Object (DAO) for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
<#if ejb3>
@${pojo.importType("javax.ejb.Stateless")}
</#if>
public class ${declarationName}DAO extends BaseDAO {
  /**
   * Logger for this class
   */
	private static final ${pojo.importType("org.apache.log4j.Logger")} logger = Logger.getLogger(${pojo.getDeclarationName()}DAO.class);
<#if ejb3>
    @${pojo.importType("javax.persistence.PersistenceContext")} private ${pojo.importType("javax.persistence.EntityManager")} entityManager;
    
    public void persist(${declarationName} transientInstance) {
        logger.debug("persisting ${declarationName} instance");
        try {
            entityManager.persist(transientInstance);
            logger.debug("persist successful");
        }
        catch (RuntimeException re) {
            logger.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(${declarationName} persistentInstance) {
        logger.debug("removing ${declarationName} instance");
        try {
            entityManager.remove(persistentInstance);
            logger.debug("remove successful");
        }
        catch (RuntimeException re) {
            logger.error("remove failed", re);
            throw re;
        }
    }
    
    public ${declarationName} merge(${declarationName} detachedInstance) {
        logger.debug("merging ${declarationName} instance");
        try {
            ${declarationName} result = entityManager.merge(detachedInstance);
            logger.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.error("merge failed", re);
            throw re;
        }
    }
    
<#if clazz.identifierProperty?has_content>    
    public ${declarationName} findById( ${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        logger.debug("getting ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = entityManager.find(${pojo.getDeclarationName()}.class, id);
            logger.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.error("get failed", re);
            throw re;
        }
    }
</#if>
<#else>    
    // private final ${pojo.importType("org.hibernate.SessionFactory")} sessionFactory = HibernateUtil.getSessionFactory();
    
    public ${declarationName}DAO (Session session)
    {
      super(session);
    }
    
    public ${pojo.importType("org.hibernate.SessionFactory")} getSessionFactory() {
      return super.getSessionFactory();
    }
    
    public void persist(${declarationName} transientInstance) {
        logger.debug("persisting ${declarationName} instance");
        try {
            session.persist(transientInstance);
            logger.debug("persist successful");
        }
        catch (RuntimeException re) {
            logger.error("persist failed", re);
            throw re;
        }
    }
    
    public Integer save(${declarationName} instance)
    {
      return (Integer) session.save(instance);
    }
    
    public void attachDirty(${declarationName} instance) {
        logger.debug("attaching dirty ${declarationName} instance");
        try {
            session.saveOrUpdate(instance);
            logger.debug("attach successful");
        }
        catch (RuntimeException re) {
            logger.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(${declarationName} instance) {
        logger.debug("attaching clean ${declarationName} instance");
        try {
            session.lock(instance, ${pojo.importType("org.hibernate.LockMode")}.NONE);
            logger.debug("attach successful");
        }
        catch (RuntimeException re) {
            logger.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(${declarationName} persistentInstance) {
        logger.debug("deleting ${declarationName} instance");
        try {
            session.delete(persistentInstance);
            logger.debug("delete successful");
        }
        catch (RuntimeException re) {
            logger.error("delete failed", re);
            throw re;
        }
    }
    
    public ${declarationName} merge(${declarationName} detachedInstance) {
        logger.debug("merging ${declarationName} instance");
        try {
            ${declarationName} result = (${declarationName}) session
                    .merge(detachedInstance);
            logger.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.error("merge failed", re);
            throw re;
        }
    }
    
<#if clazz.identifierProperty?has_content>
    public ${declarationName} findById( ${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        logger.debug("getting ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = (${declarationName}) session
                    .get("${clazz.entityName}", id);
            if (instance==null) {
                logger.debug("get successful, no instance found");
            }
            else {
                logger.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            logger.error("get failed", re);
            throw re;
        }
    }
</#if>
    
<#if clazz.hasNaturalId()>
    public ${declarationName} findByNaturalId(${c2j.asNaturalIdParameterList(clazz)}) {
        logger.debug("getting ${declarationName} instance by natural id");
        try {
            ${declarationName} instance = (${declarationName}) session
                    .createCriteria("${clazz.entityName}")
<#if jdk5>
                    .add( ${pojo.staticImport("org.hibernate.criterion.Restrictions", "naturalId")}()
<#else>
                   .add( ${pojo.importType("org.hibernate.criterion.Restrictions")}.naturalId()
</#if>                    
<#foreach property in pojo.getAllPropertiesIterator()>
<#if property.isNaturalIdentifier()>
                            .set("${property.name}", ${property.name})
</#if>
</#foreach>
                        )
                    .uniqueResult();
            if (instance==null) {
                logger.debug("get successful, no instance found");
            }
            else {
                logger.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            logger.error("query failed", re);
            throw re;
        }
    }
</#if>    
@SuppressWarnings("unchecked")
<#if jdk5>
    public ${pojo.importType("java.util.List")}<${declarationName}> findByExample(${declarationName} instance) {
<#else>
    public ${pojo.importType("java.util.List")} findByExample(${declarationName} instance) {
</#if>
        logger.debug("finding ${declarationName} instance by example");
        try {
<#if jdk5>
            ${pojo.importType("java.util.List")}<${declarationName}> results = (List<${declarationName}>) session
<#else>
            ${pojo.importType("java.util.List")} results = session
</#if>
                    .createCriteria("${clazz.entityName}")
<#if jdk5>
                    .add( ${pojo.staticImport("org.hibernate.criterion.Example", "create")}(instance) )
<#else>
                    .add(${pojo.importType("org.hibernate.criterion.Example")}.create(instance))
</#if>
            .list();
            logger.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            logger.error("find by example failed", re);
            throw re;
        }
    } 
<#foreach queryName in cfg.namedQueries.keySet()>
<#if queryName.startsWith(clazz.entityName + ".")>
<#assign methname = c2j.unqualify(queryName)>
<#assign params = cfg.namedQueries.get(queryName).parameterTypes><#assign argList = c2j.asFinderArgumentList(params, pojo)>
<#if jdk5 && methname.startsWith("find")>
    public ${pojo.importType("java.util.List")}<${declarationName}> ${methname}(${argList}) {
<#elseif methname.startsWith("count")>
    public int ${methname}(${argList}) {
<#else>
    public ${pojo.importType("java.util.List")} ${methname}(${argList}) {
</#if>
        ${pojo.importType("org.hibernate.Query")} query = session
                .getNamedQuery("${queryName}");
<#foreach param in params.keySet()>
<#if param.equals("maxResults")>
		query.setMaxResults(maxResults);
<#elseif param.equals("firstResult")>
        query.setFirstResult(firstResult);
<#else>
        query.setParameter("${param}", ${param});
</#if>
</#foreach>
<#if jdk5 && methname.startsWith("find")>
        return (List<${declarationName}>) query.list();
<#elseif methname.startsWith("count")>
        return ( (Integer) query.uniqueResult() ).intValue();
<#else>
        return query.list();
</#if>
    }
</#if>
</#foreach></#if>
}
</#assign>

${pojo.generateImports()}
${classbody}

package com.boubei.tss.demo.resource;

import java.util.List;

import com.boubei.tss.framework.persistence.ITreeSupportDao;
import com.boubei.tss.um.permission.filter.PermissionFilter4Branch;
import com.boubei.tss.um.permission.filter.PermissionTag;
 
public interface XXDao extends ITreeSupportDao<XX> {
	
    @PermissionTag(
    		application = XX.APPLICATION,
    		resourceType = XX.RESOURCE_TYPE,
            filter = PermissionFilter4Branch.class)
    List<XX> getChildrenById(Long id, String operationId);
    
    XX deleteXX(XX XX);
 
}

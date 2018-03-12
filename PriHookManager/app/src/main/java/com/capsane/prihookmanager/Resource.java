package com.capsane.prihookmanager;

/**
 * Created by capsane on 18-3-9.
 *
 */

public class Resource {

    private String resourceType;

    private int accessFlag;

    public Resource(String resourceType, int accessFlag) {
        this.resourceType = resourceType;
        this.accessFlag = accessFlag;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

}

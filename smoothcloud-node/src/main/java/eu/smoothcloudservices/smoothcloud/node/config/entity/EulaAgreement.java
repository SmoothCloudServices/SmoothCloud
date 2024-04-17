package eu.smoothcloudservices.smoothcloud.node.config.entity;

import java.io.Serializable;

public class EulaAgreement implements Serializable {

    private boolean agreement;

    public EulaAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public boolean getAgreement() {
        return agreement;
    }
}

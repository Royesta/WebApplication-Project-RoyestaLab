package com.royesta.webapp.service;

import com.royesta.webapp.entity.AbstractEntity;
import java.io.Serializable;

public interface Service <T extends AbstractEntity<?>, ID extends Serializable> {
}

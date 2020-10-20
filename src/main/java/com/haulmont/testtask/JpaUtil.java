package com.haulmont.testtask;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static volatile EntityManagerFactory emFactory;

    private JpaUtil() {
    }

    public static EntityManagerFactory getEmFactory() {
        EntityManagerFactory localEmFactory = emFactory;
        if (localEmFactory == null) {
            synchronized (JpaUtil.class) {
                localEmFactory = emFactory;
                if (localEmFactory == null) {
                    emFactory = localEmFactory = Persistence.createEntityManagerFactory("com.haulmont.test_task");

                }
            }
        }
        return localEmFactory;
    }
}

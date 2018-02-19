package com.yanevskyy.y.bythewayanalitics;

/**
 * Created by Y on 09.02.18.
 */

import com.firebase.mm.myapplication.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by MM on 06.02.2018.
 */

public class UserDao implements Serializable {
    private Collection<User> users;

    public UserDao(Collection<User> users ) {
        this.users = users;
    }

    public Collection<User> getUsers() {
        return users;
    }

}
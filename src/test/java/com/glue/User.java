package com.glue;

import org.apache.commons.io.FileUtils;
import java.io.IOException;
import support.*;

public class User {
    private String userName;

    public String getName() {
      return this.userName;
    }

    public void setName (String userName) {
      this.userName = userName;
    }
  }
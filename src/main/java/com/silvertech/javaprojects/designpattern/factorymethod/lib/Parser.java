package com.silvertech.javaprojects.designpattern.factorymethod.lib;

import java.io.File;
import java.util.List;

public interface Parser {
  public List<Record> parse(File file);
}

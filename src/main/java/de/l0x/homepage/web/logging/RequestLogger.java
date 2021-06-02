package de.l0x.homepage.web.logging;

import javax.servlet.http.HttpServletRequest;

public interface RequestLogger {

    void log(HttpServletRequest request);

}

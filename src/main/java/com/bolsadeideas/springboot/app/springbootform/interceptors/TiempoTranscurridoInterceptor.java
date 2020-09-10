package com.bolsadeideas.springboot.app.springbootform.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    private static final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        /*
         * La siguiente condición nos permite NO aplicar el iterceptor a los métodos
         * post ya que esto no es posible configurar desde:
         * registry.addInterceptor(tiempoTranscurridoHandlerInterceptor).addPathPatterns
         * ("/form/**"); De tal forma que solamente se aplicarán los interceptores al
         * método get del controlador.
         */
        if (request.getMethod().equalsIgnoreCase("post")) {
            return true;
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger.info("Es un método controlador: " + handlerMethod.getMethod().getName());
        }

        Long initTime = System.currentTimeMillis();

        logger.info("Interceptando handler: " + handler);
        logger.info("TiempoTranscurridoInterceptor.preHandle() iniciando ...");
        logger.info("initTime: " + initTime.toString());

        request.setAttribute("initTime", initTime);

        int timeToSleep = random.nextInt(100);

        logger.info("timeToSleep: " + timeToSleep);

        Thread.sleep(timeToSleep);

        /*
         * Las siguientes lineas ejemplifican un caso en el que por alguna razón el
         * interceptor manda error y no continúa el flujo haciendo uso del
         * request.getContextPath() el cual retorna el path de la aplicación principal y
         * a la cual le agregamos el path de otra página o controlador.
         */
        // response.sendRedirect(request.getContextPath().concat("/login"));
        // return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        if (!request.getMethod().equalsIgnoreCase("post")) {
            long endTime = System.currentTimeMillis();
            long initTime = (Long) request.getAttribute("initTime");

            Long processTime = endTime - initTime;

            logger.info("initTime: " + initTime);
            logger.info("endTime: " + endTime);
            logger.info("processTime: " + processTime);
            logger.info("TiempoTranscurridoInterceptor.postHandle() concluyendo ...");
            logger.info("Tiempo transcurrido ".concat(processTime.toString()).concat(" milliseconds"));

            if (handler instanceof HandlerMethod && modelAndView != null) {
                modelAndView.addObject("processTime", processTime);
            }
        }
    }
}
package config;

import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import controller.AdminCtr;
import controller.UserCtr;
import model.UserMdl;
import model.XmUserMdl;

/**
 * Created by Administrator on 2016/10/28.
 */

public class HelloConf  extends JFinalConfig {

    public void configConstant(Constants constants) {

        constants.setDevMode(true);
        constants.setEncoding("utf-8");
        PropKit.use("constant.properties");
        constants.setViewType(ViewType.JSP);
        constants.setBaseViewPath("/WEB-INF/view");

    }

    public void configRoute(Routes routes) {
        //        me.add("/", IndexController.class);	// 第三个参数为该Controller的视图存放路径
        routes.add("user", UserCtr.class);//http://localhost/user/test 对应到usercontroller的test（）；
        routes.add("admin", AdminCtr.class);

        //3ed参数是viewpath，默认为controler的名字
    }

    public static C3p0Plugin createC3p0Plugin() {
        return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }


    public void configPlugin(Plugins plugins) {
        C3p0Plugin C3p0Plugin = createC3p0Plugin();
        plugins.add(C3p0Plugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
        plugins.add(arp);

        arp.addMapping("userinfo", UserMdl.class);
        arp.addMapping("xmuser", XmUserMdl.class);
    }

    public void configInterceptor(Interceptors interceptors) {

    }
    public void configHandler(Handlers me) {
        // TODO Auto-generated method stub
//		me.add(new ContextPathHandler("basePath"));

    }
}

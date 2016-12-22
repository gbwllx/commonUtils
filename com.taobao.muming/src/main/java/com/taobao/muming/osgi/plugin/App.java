package plugin;

import plugin.api.ICheckService;
import plugin.api.IRuleService;


/**
 * Created by gubing.gb on 2016/12/9.
 */
public class App {
    public static void main(String[] args) {
        PluginManager pluginManager = PluginManager.getMgr();
        pluginManager.addExternalJar("D:\\osgi");
        IRuleService ruleService = pluginManager.getPlugin("plugin.impl.RuleServiceImpl", IRuleService.class);
        ICheckService checkService = pluginManager.getPlugin("plugin.impl.CheckServiceImpl", ICheckService.class);
        System.out.println(ruleService.add("rule"));
        System.out.println(checkService.check("money"));

    }
}

# PluginGenerator
PluginGenerator is a small application that will ask you
three basic questions and will then generate a basic Bukkit plugin.

The three questions it asks are:
* What is the name of the plugin?
* What is the package name of the plugin?
* Do you want MockBukkit support?

It will then create a folder with the name of the plugin in your working directory
and put all the files inside of it.

There is also an extra target, `deploy`.
This will create the plugin jar and copy it to the folder server/plugins.
This makes testing much easier as you just have to symlink your server root to `server/`

# thump
基于thumbnailator的图片批量压缩工具，已经打包为exe文件，但是必须在有jdk的环境下才可以直接运行
使用方法：<br>
1.把thump.exe拷贝到需要压缩图片的文件的文件夹下面，直接双击运行，文件夹下会生成一个thump文件夹，递归的把当前目录下所有的图片找出来压缩，默认压缩率为   0.7 <br/>
2.使用命令行运行，./thump.exe 后面可以带两个参数，一个小于1的整数，表示压缩率，一个boolean类型的值（true或者false）表示是否把图片放在一起，而不递归创建原文件夹下的目录结构

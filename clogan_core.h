
#ifndef CLOGAN_LOGAN_CORE_H
#define CLOGAN_LOGAN_CORE_H

#ifdef __cplusplus
extern "C"
{
#endif

#include <zlib.h>
#include <stdlib.h>
#include "logan_config.h"

int
clogan_init(const char *cache_dirs, const char *path_dirs, int max_file, const char *encrypt_key16,
            const char *encrypt_iv16);

/**
 @brief 打开一个文件的写入
 @param pathname  文件名称
 */
int clogan_open(const char *pathname); //打开一个文件的写入

/**
 @brief 写入数据 按照顺序和类型传值(强调、强调、强调)
 @param flag 日志类型 (int)
 log 日志内容 (char*)
 local_time 日志发生的本地时间，形如1502100065601 (long long)
 thread_name 线程名称 (char*)
 thread_id 线程id (long long) 为了兼容JAVA
 is_main 是否为主线程，0为是主线程，1位非主线程 (int)
 */
int
clogan_write(int flag, char *log, char * local_time, char *thread_name, long long thread_id,
             int is_main);

/**
 @brief 强制写入文件。建议在崩溃或者退出程序的时候调用
 */
int clogan_flush(void);

/**
 @brief 是否为debug环境。debug环境将输出过程日志到控制台中
 @param debug 1为开启 0为关闭 默认为0
 */
void clogan_debug(int debug);

#ifdef __cplusplus
}
#endif
#endif //CLOGAN_LOGAN_CORE_H

package com.liy.quartz;

import com.liy.domain.entity.Job;
import org.quartz.JobExecutionContext;

/**
 * @author CodeScholar
 * @date 2023年4月9日
 * @apiNote 定时任务处理（允许并发执行）
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}

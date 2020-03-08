package com.springbootbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbootbatch.batch.Processor;
import com.springbootbatch.batch.Reader;
import com.springbootbatch.batch.Writer;
import com.springbootbatch.model.WriterSO;

@Configuration
@EnableBatchProcessing
public class CustomBatchConfiguration extends DefaultBatchConfigurer {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private Reader reader;

	@Autowired
	private Processor processor;

	@Autowired
	private Writer writer;

	@Bean
	public MapJobRepositoryFactoryBean mapJobRepositoryFactory() throws Exception {
		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(new ResourcelessTransactionManager());
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	public JobRepository jobRepository(MapJobRepositoryFactoryBean repositoryFactory) throws Exception {
		repositoryFactory.setTransactionManager(new ResourcelessTransactionManager());
		return repositoryFactory.getObject();
	}

	@Bean
	public JobExplorer jobExplorer(MapJobRepositoryFactoryBean repositoryFactory) {
		return new SimpleJobExplorer(repositoryFactory.getJobInstanceDao(), repositoryFactory.getJobExecutionDao(),
				repositoryFactory.getStepExecutionDao(), repositoryFactory.getExecutionContextDao());
	}

	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<WriterSO, WriterSO>chunk(1).reader(reader).processor(processor)
				.writer(writer).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobExecutionListener();
	}
}

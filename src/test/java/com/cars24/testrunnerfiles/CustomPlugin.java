package com.cars24.testrunnerfiles;

import java.io.File;
import java.util.Collection;

import java.util.Iterator;

import java.util.Set;
import java.util.TreeSet;

import io.cucumber.core.exception.CucumberException;
import io.cucumber.messages.Messages.TestCaseStarted;
import io.cucumber.messages.Messages.TestRunStarted;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestSourceRead;

public class CustomPlugin implements EventListener {
	private File reportDir;
	@SuppressWarnings("unused")
	private Set<String> allTags = new TreeSet<String>();

	public CustomPlugin() {
		createOutputDir(System.getProperty("user.dir") + "/run.log");
	}

	private void createOutputDir(String outputDir) {
		reportDir = new File(outputDir);
		if (!reportDir.exists() && !reportDir.mkdirs()) {
			throw new CucumberException("Failed to create dir: " + outputDir);
		}
	}

	private EventHandler<TestRunStarted> runStartedHandler = new EventHandler<TestRunStarted>() {
		@Override
		public void receive(TestRunStarted event) {
			startReport(event);
		}
	};

	private EventHandler<TestSourceRead> runSourceRead = new EventHandler<TestSourceRead>() {
		@Override
		public void receive(TestSourceRead event) {
			getsource(event);
		}
	};

	private EventHandler<TestCaseStarted> testCaseStarted = new EventHandler<TestCaseStarted>() {

		@Override
		public void receive(TestCaseStarted event) {
			getTestCaseStarted(event);
		}
	};

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestRunStarted.class, runStartedHandler);
		publisher.registerHandlerFor(TestSourceRead.class, runSourceRead);
		publisher.registerHandlerFor(TestCaseStarted.class, testCaseStarted);
	}

	private void startReport(TestRunStarted event) {
		System.out.println(event.getTimestamp());
	}

	private void getsource(TestSourceRead event) {
		String source=event.getSource();
		System.out.println(source);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getTestCaseStarted(TestCaseStarted event) {
		Collection values = event.getAllFields().values();
		Iterator<String> iterator = values.iterator();
		while (iterator.hasNext()) {
			System.out.println("value= " + iterator.next());
		}

	}

	/**
	 * TestRunStarted – Event sent when test run in started. Contains the timestamp
	 * of the test run start. TestSourceRead – Event sent when feature file is read.
	 * Contains the location of the feature file and its contents. TestCaseStarted –
	 * Event sent before scenario execution. Contains the scenario details like uri,
	 * line, steps, tags etc. TestStepStarted – Event sent before step execution.
	 * Contains the step details. TestStepFinished – Event sent after step
	 * execution. Contains the step details and result of the step. TestCaseFinished
	 * – Event sent after scenario execution. Contains the scenario details and test
	 * case result. TestRunFinished – Event sent when test run in finished. Contains
	 * the timestamp of the test run end. EmbedEvent – Event sent when
	 * scenario.embed is called inside a hook. Contains the byte array and mime
	 * type. WriteEvent – Event sent when scenario.write is called inside a hook.
	 * Contains the text. SnippetsSuggestedEvent – Event sent when step cannot be
	 * matched to a step definition. Contains details like uri, locations etc.
	 */

}

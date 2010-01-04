package org.openmrs.module.dataset.definition.evaluator;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataset.DataSet;
import org.openmrs.module.dataset.DataSetRow;
import org.openmrs.module.dataset.column.DataSetColumn;
import org.openmrs.module.dataset.definition.DataSetDefinition;
import org.openmrs.module.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.dataset.definition.service.DataSetDefinitionService;
import org.openmrs.module.evaluation.EvaluationContext;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;

public class SqlDataSetDefinitionEvaluatorTest extends BaseModuleContextSensitiveTest {

	
	
	private static Log log = LogFactory.getLog(SqlDataSetDefinitionEvaluatorTest.class);
	
	
    /**
     * @see {@link JoinDataSetDefinitionEvaluator#evaluate(DataSetDefinition,EvaluationContext)}
     */
	@Test
    @Verifies(value = "should join two plain datasets correctly", method = "evaluate(DataSetDefinition,EvaluationContext)")
    public void evaluate_shouldJoinTwoPlainDatasetsCorrectly() throws Exception {
    	SqlDataSetDefinition jdbcDSD = new SqlDataSetDefinition("test", "test", "select * from patient");
        DataSet dataset = Context.getService(DataSetDefinitionService.class).evaluate(jdbcDSD, new EvaluationContext());
        DataSetRow dataSetRow = (DataSetRow) dataset.iterator().next();
        for (Map.Entry<DataSetColumn, Object> entry : dataSetRow.getColumnValues().entrySet()) {
        	log.info("entry: " + entry);
        }
        
        /*
        ReportData temp = new ReportData();
        Map<String, DataSet> dataSets = new HashMap<String, DataSet>();
        dataSets.put("dataset", dataset);
        temp.setDataSets(dataSets);
        
        TsvReportRenderer renderer = new TsvReportRenderer();
        renderer.render(temp, null, System.out);
        */
    }
    
}
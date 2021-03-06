/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.dialect;

import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.spi.RowSelection;
import org.hibernate.testing.RequiresDialect;
import org.hibernate.testing.TestForIssue;
import org.hibernate.testing.junit4.BaseNonConfigCoreFunctionalTestCase;
import org.junit.Test;


import static org.junit.Assert.assertEquals;


public class InformixLimitHandlerTestCase extends
		BaseNonConfigCoreFunctionalTestCase {

	private final String TEST_SQL = "SELECT field FROM table";

	@Test
	@TestForIssue(jiraKey = "HHH-11509")
	@RequiresDialect(InformixDialect.class)
	public void testCache71DialectLegacyLimitHandler() {
		assertLimitHandlerEquals( "SELECT SKIP 2 FIRST 5 field FROM table" );
	}


	private void assertLimitHandlerEquals(String sql) {
		assertEquals( sql, getDialect().getLimitHandler().processSql( TEST_SQL, toRowSelection( 3, 5 ) ) );
	}

	private RowSelection toRowSelection(int firstRow, int maxRows) {
		RowSelection selection = new RowSelection();
		selection.setFirstRow( firstRow );
		selection.setMaxRows( maxRows );
		return selection;
	}
}

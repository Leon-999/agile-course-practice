package ru.unn.agile.VolumesComputer.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ComputerViewModelLogTest {
    private ComputerViewModel viewModel;
    @Before
    public void initialize() {
        viewModel = new ComputerViewModel(new LoggerFake());
    }
    @Test
    public void logSetFigureAddLine() {
        assertEquals(viewModel.getLog().size(), 1);
    }
    @Test
    public void logResetFigureAddLine() {
        viewModel.setFigure(FigureName.SPHEROID);
        assertEquals(viewModel.getLog().size(), 2);
    }
    @Test
    public void logSetParameter1dontAddLine() {
        viewModel.setParameter1("10");
        assertEquals(viewModel.getLog().size(), 1);
    }
    @Test
    public void logSetParameter2dontAddLine() {
        viewModel.setParameter2("10");
        assertEquals(viewModel.getLog().size(), 1);
    }
    @Test
    public void logSetParameter3dontAddLine() {
        viewModel.setParameter3("10");
        assertEquals(viewModel.getLog().size(), 1);
    }
    @Test
    public void logUpdate1addLine() {
        viewModel.setParameter1("10");
        viewModel.update1();
        assertEquals(viewModel.getLog().size(), 2);
    }
    @Test
    public void logUpdate2addLine() {
        viewModel.setParameter2("10");
        viewModel.update2();
        assertEquals(viewModel.getLog().size(), 2);
    }
    @Test
    public void logUpdate3addLine() {
        viewModel.setParameter3("10");
        viewModel.update3();
        assertEquals(viewModel.getLog().size(), 2);
    }
    @Test
    public void logSetFigure() {
        assertThat(viewModel.getLog().get(0), containsString(getLogSetFigure()));
    }
    @Test
    public void logResetFigure() {
        viewModel.setFigure(FigureName.SPHEROID);
        assertThat(viewModel.getLog().get(1), containsString(getLogSetFigure()));
    }
    @Test
    public void logSetParameter1() {
        viewModel.setParameter1("10");
        viewModel.update1();
        assertThat(
                viewModel.getLog().get(1),
                containsString(getLogSetParameter(viewModel.getParameter1(), 0)));
    }
    @Test
    public void logSetParameter2() {
        viewModel.setParameter2("10");
        viewModel.update2();
        assertThat(
                viewModel.getLog().get(1),
                containsString(getLogSetParameter(viewModel.getParameter2(), 1)));
    }
    @Test
    public void logSetParameter3() {
        viewModel.setParameter3("10");
        viewModel.update3();
        assertThat(
                viewModel.getLog().get(1),
                containsString(getLogSetParameter(viewModel.getParameter3(), 2)));
    }
    @Test
    public void logSolveBadParse() {
        viewModel.setParameter1("xe");
        viewModel.update1();
        viewModel.solve();
        assertThat(viewModel.getLog().get(2), containsString(getLogSolveBadParse()));
    }
    @Test
    public void logSolveBadParameters() {
        viewModel.setParameter1("-1");
        viewModel.setParameter2("1");
        viewModel.setParameter3("2");
        viewModel.update1();
        viewModel.update2();
        viewModel.update3();
        viewModel.solve();
        assertThat(viewModel.getLog().get(4), containsString(getLogSolveBadParameters()));
    }
    @Test
    public void logSolveGood() {
        viewModel.setParameter1("1");
        viewModel.setParameter2("1");
        viewModel.setParameter3("2");
        viewModel.update1();
        viewModel.update2();
        viewModel.update3();
        viewModel.solve();
        assertThat(viewModel.getLog().get(4), containsString(getLogSolveSuccess()));
    }
    public void setViewModel(final ComputerViewModel viewModel) {
        this.viewModel = viewModel;
    }
    private String getLogSetFigure() {
        String logString = LogString.SET_FIGURE.toString();
        String figName = viewModel.getFigure().toString();
        return String.format(logString, figName);
    }
    private String getLogSetParameter(final String parameter, final int index) {
        String logString = LogString.SET_PARAMETER.toString();
        FigureName fig = viewModel.getFigure();
        String parameterName = fig.getParametersNames()[index];
        return String.format(logString, parameterName, parameter);
    }
    private String getLogSolveBadParse() {
        return LogString.SOLVE_BAD_PARSE.toString();
    }
    private String getLogSolveBadParameters() {
        return LogString.SOLVE_BAD_PARAMETERS.toString();
    }
    private String getLogSolveSuccess() {
        String logString = LogString.SOLVE.toString();
        String vol = viewModel.getVolume();
        return String.format(logString, vol);
    }
}

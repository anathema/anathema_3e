package net.sf.anathema.gis.main.demo;

import gis.gisterm.GisTermUtilities;
import gis.gisterm.map.DisplayToolbar;
import gis.gisterm.map.GisView;
import gis.gisterm.map.IGisView;
import gis.gisterm.map.RasterCatalogLayer;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;

import de.disy.gis.gisterm.imagecatalog.ImageCatalogFactory;
import de.disy.gis.gisterm.map.BasicMapModel;
import de.disy.gis.gisterm.map.IMapModel;
import de.disy.gis.gisterm.map.theme.LayerTheme;
import de.disy.gisterm.pro.map.extent.DefaultScaleView;
import de.disy.gisterm.pro.map.view.configuration.IMapViewConfiguration;
import de.jdemo.extensions.SwingDemoCase;

// NOT_PUBLISHED
public class SimpleMapDemo extends SwingDemoCase {

  public void demoGisView() throws Exception {
    show(createPlatformlessGisView().getContent());
  }

  private IGisView createPlatformlessGisView() {
    IMapModel mapModel = new BasicMapModel();
    IMapViewConfiguration gisViewConfiguration = new NullMapViewConfiguration();
    return new GisView(mapModel, gisViewConfiguration, null);
  }

  public void demoGisViewWithToolbar() throws Exception {
    IGisView gisView = createPlatformlessGisView();
    DisplayToolbar toolbar = createToolbar(gisView);
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(toolbar, BorderLayout.NORTH);
    panel.add(gisView.getContent(), BorderLayout.CENTER);
    show(panel);
  }

  public void demoGisViewWithToolbarAndScale() throws Exception {
    IGisView gisView = createPlatformlessGisView();
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(createToolbar(gisView), BorderLayout.NORTH);
    panel.add(gisView.getContent(), BorderLayout.CENTER);
    panel.add(createScaleView(gisView), BorderLayout.SOUTH);
    show(panel);
  }

  private DisplayToolbar createToolbar(IGisView gisView) {
    return GisTermUtilities.createMinimalDisplayToolBar(gisView);
  }

  private DefaultScaleView createScaleView(IGisView gisView) {
    DefaultScaleView scaleView = new DefaultScaleView();
    scaleView.setModel(gisView);
    return scaleView;
  }

  public void demoCreationKarte() throws Exception {
    IGisView gisView = createPlatformlessGisView();
    RasterCatalogLayer catalogLayer = ImageCatalogFactory.createUnmonitoredImageCatalogLayer(new File(
        "./gisdata/Creation Xeria.icat")); //$NON-NLS-1$
    gisView.getMapModel().getThemeModel().addTheme(new LayerTheme(catalogLayer));
    gisView.getMapModel().getViewProperties().setSuggestedWorldBox(catalogLayer.getExtent());
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(createToolbar(gisView), BorderLayout.NORTH);
    panel.add(gisView.getContent(), BorderLayout.CENTER);
    panel.add(createScaleView(gisView), BorderLayout.SOUTH);
    show(panel);
  }
}
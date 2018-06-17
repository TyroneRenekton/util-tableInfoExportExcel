<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Created>2015-06-05T18:19:34Z</Created>
  <LastSaved>2018-06-17T15:49:05Z</LastSaved>
  <Version>16.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
  <RemovePersonalInformation/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>12648</WindowHeight>
  <WindowWidth>22260</WindowWidth>
  <WindowTopX>32767</WindowTopX>
  <WindowTopY>32767</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Bottom"/>
   <Borders/>
   <Font ss:FontName="等线" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s16">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="等线" x:CharSet="134" ss:Size="11" ss:Color="#000000"
    ss:Bold="1"/>
  </Style>
  <Style ss:ID="s18">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="等线" x:CharSet="134" ss:Size="14" ss:Color="#000000"
    ss:Bold="1"/>
  </Style>
  <Style ss:ID="s20">
   <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
   <Font ss:FontName="等线" x:CharSet="134" ss:Size="11" ss:Color="#000000"
    ss:Bold="1"/>
  </Style>
  <Style ss:ID="s21">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="等线" x:CharSet="134" ss:Size="20" ss:Color="#000000"
    ss:Bold="1"/>
  </Style>
 </Styles>
 <Worksheet ss:Name="Sheet1">
  <Table ss:ExpandedColumnCount="4" ss:ExpandedRowCount="300" x:FullColumns="1"
   x:FullRows="1" ss:StyleID="s20" ss:DefaultColumnWidth="106.2"
   ss:DefaultRowHeight="22.799999999999997">
   <Column ss:StyleID="s20" ss:AutoFitWidth="0" ss:Width="140.4"/>
   <Column ss:StyleID="s20" ss:AutoFitWidth="0" ss:Width="109.8"/>
   <Column ss:StyleID="s20" ss:AutoFitWidth="0" ss:Width="121.2"/>
   <Column ss:StyleID="s20" ss:AutoFitWidth="0" ss:Width="262.2"/>
   <Row ss:AutoFitHeight="0" ss:Height="46.2">
    <Cell ss:MergeAcross="3" ss:StyleID="s21"><Data ss:Type="String">${tableName!}</Data></Cell>
   </Row>
   <Row ss:AutoFitHeight="0" ss:StyleID="s16">
    <Cell ss:StyleID="s18"><Data ss:Type="String">列名</Data></Cell>
    <Cell ss:StyleID="s18"><Data ss:Type="String">数据类型</Data></Cell>
    <Cell ss:StyleID="s18"><Data ss:Type="String">默认</Data></Cell>
    <Cell ss:StyleID="s18"><Data ss:Type="String">注释</Data></Cell>
   </Row>
   <#list list as item>
   <Row ss:AutoFitHeight="0">
    <Cell><Data ss:Type="String">${item.columnName!}</Data></Cell>
    <Cell><Data ss:Type="String">${item.columnType!}</Data></Cell>
    <Cell><Data ss:Type="String">${item.columnDefault!}</Data></Cell>
    <Cell><Data ss:Type="String">${item.columnComment!}</Data></Cell>
   </Row>
   </#list>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Unsynced/>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <HorizontalResolution>600</HorizontalResolution>
    <VerticalResolution>600</VerticalResolution>
   </Print>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>4</ActiveRow>
     <ActiveCol>3</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>

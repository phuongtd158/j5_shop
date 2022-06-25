package com.poly.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.poly.models.CartModel;

@Service
public class PDFUtils {

	public void prinfPDF(HashMap<Integer, CartModel> cart, Integer invoiceId, Date date, String name, String address,
			String email, String phoneNumber, double totalPrice) throws FileNotFoundException {
		String path = "C:\\Users\\ADMIN\\eclipse-workspace\\Assignment_Java5\\src\\main\\webapp\\invoice";
		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = "invoice.pdf";

		PdfWriter pdfWriter = new PdfWriter(path + "/" + fileName);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfDocument);

		float threeCols = 190f;
		float threeColWidth[] = { threeCols, threeCols, threeCols };
		float twoCols = 258f;
		float twoCol150 = twoCols + 150f;
		float twoColumnWidth[] = { twoCol150, twoCols };
		float fullWidth[] = { threeCols * 3 };
		float oneColumnWidth[] = { twoCol150 };
		Paragraph oneSpace = new Paragraph("\n");

		// Header
		Table table = new Table(twoColumnWidth);
		table.addCell(new Cell().add("Invoice").setBorder(Border.NO_BORDER).setBold().setFontSize(24f));

		Table nestedTable = new Table(new float[] { twoCols / 2, twoCols / 2 });
		nestedTable.addCell(getHeaderTextCell("Invoice Id:"));
		nestedTable.addCell(getHeaderTextCellValue(String.valueOf(invoiceId)));
		nestedTable.addCell(getHeaderTextCell("Invoice Date:"));
		nestedTable.addCell(getHeaderTextCellValue(String.valueOf(date)));

		table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));

		Border grayBorder = new SolidBorder(Color.GRAY, 2f);
		Table dividerTable = new Table(fullWidth);
		dividerTable.setBorder(grayBorder);
		document.add(table);
		document.add(oneSpace);
		document.add(dividerTable);
		document.add(oneSpace);

		Table twoColsTable = new Table(twoColumnWidth);
		twoColsTable.addCell(getBillingAndShippingCell("Billing Information: "));
		document.add(twoColsTable.setMarginBottom(12f));

		Table twoColsTable3 = new Table(twoColumnWidth);
		twoColsTable3.addCell(getCell10fLeft("Name:", true));
		twoColsTable3.addCell(getCell10fLeft(name, false));
		twoColsTable3.addCell(getCell10fLeft("Address:", true));
		twoColsTable3.addCell(getCell10fLeft(address, false));
		twoColsTable3.addCell(getCell10fLeft("Email:", true));
		twoColsTable3.addCell(getCell10fLeft(email, false));
		twoColsTable3.addCell(getCell10fLeft("Phone Number:", true));
		twoColsTable3.addCell(getCell10fLeft(phoneNumber, false));

		document.add(twoColsTable3);
		document.add(oneSpace);

		Table dividerTable2 = new Table(fullWidth);
		Border dgb = new DashedBorder(Color.GRAY, 0.5f);
		document.add(dividerTable2.setBorder(dgb));

		Paragraph productParagraph = new Paragraph("Product");
		document.add(productParagraph.setBold());
		Table threeColTable1 = new Table(threeColWidth);
		threeColTable1.setBackgroundColor(Color.BLACK, 0.7f);

		threeColTable1.addCell(new Cell().add("Name").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
		threeColTable1.addCell(new Cell().add("Quantity").setBold().setFontColor(Color.WHITE)
				.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
		threeColTable1.addCell(new Cell().add("Price").setBold().setFontColor(Color.WHITE)
				.setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
		document.add(threeColTable1);

		Table threeColTable2 = new Table(threeColWidth);

		for (Map.Entry<Integer, CartModel> entry : cart.entrySet()) {
			threeColTable2.addCell(new Cell().add(entry.getValue().getProduct().getName()).setBorder(Border.NO_BORDER)
					.setMarginLeft(10f));
			threeColTable2.addCell(new Cell().add(String.valueOf(entry.getValue().getQuantity()))
					.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
			threeColTable2.addCell(new Cell().add(String.valueOf(entry.getValue().getProduct().getPrice()))
					.setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
		}
		document.add(threeColTable2.setMarginBottom(20f));

		float oneTwo[] = { threeCols + 125f, threeCols * 2 };
		Table threeColTable3 = new Table(oneTwo);
		threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		threeColTable3.addCell(new Cell().add(dividerTable2).setBorder(Border.NO_BORDER));
		document.add(threeColTable3.setMarginBottom(10f));

		Table threeColTable4 = new Table(threeColWidth);
		threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setMarginLeft(10f));
		threeColTable4
				.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
		threeColTable4.addCell(new Cell().add(String.valueOf(totalPrice)).setTextAlignment(TextAlignment.RIGHT)
				.setBorder(Border.NO_BORDER).setMarginRight(15f));

		document.add(threeColTable4.setMarginBottom(15f));
		document.add(dividerTable2);
		document.add(oneSpace);
		document.add(dividerTable.setBorder(new SolidBorder(Color.GRAY, 1)).setMarginBottom(15f));

		Table tableText = new Table(fullWidth);
		tableText.addCell(new Cell().add("Thank you for your order !").setBold().setTextAlignment(TextAlignment.CENTER)
				.setBorder(Border.NO_BORDER));
		document.add(tableText);

		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path + "/" + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
	}

	static Cell getHeaderTextCell(String textValue) {
		return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
	}

	static Cell getHeaderTextCellValue(String textValue) {
		return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
	}

	static Cell getBillingAndShippingCell(String textValue) {
		return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.LEFT);
	}

	static Cell getCell10fLeft(String textValue, Boolean isBold) {
		Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.LEFT);
		return isBold ? myCell.setBold() : myCell;
	}
}

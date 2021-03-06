import {Injectable} from '@angular/core';
import * as jsPDF from 'jspdf';

@Injectable()
export class PdfService {

  getBase64Image(img) {
    const canvas = document.createElement('canvas');
    canvas.width = img.width;
    canvas.height = img.height;
    const ctx = canvas.getContext('2d');
    ctx.drawImage(img, 0, 0);
    const dataURL = canvas.toDataURL('image/png');
    return dataURL;
  }

  donloadPDF(elementId: string) {
    const doc = new jsPDF();
    const imageData = this.getBase64Image(document.getElementById(elementId));
    doc.addImage(imageData, 'JPG', 10, 10, 180, 90);
    doc.save(elementId + '.pdf');
  }

}

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
  
}

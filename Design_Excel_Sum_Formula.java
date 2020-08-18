class Excel {
    class CellIndex {
        int h;
        char w;
        
        CellIndex(int h, char w) {
            this.h = h;
            this.w = w;
        }
    }
    
    class Cell {
        public int value;
        public List<Cell> listeners;
        public List<Cell> numbers;
        
        Cell() {
            value = 0;
            listeners = new ArrayList<>();
            numbers = new ArrayList<>();
        }
    }
    
    Cell[][] table;

    public Excel(int H, char W) {
        table = new Cell[H + 1][W + 1];
        for (int h = 1; h <= H; ++h) {
            for (int w = 'A'; w <= W; ++w) {
                table[h][w] = new Cell();
            }
        }
    }
    
    private void setValueWithCascading(Cell cell, int v) {
        
        int oldValue = cell.value;
        cell.value = v;
        
        for (Cell listener: cell.listeners) {
            setValueWithCascading(listener, listener.value + v - oldValue);
        }
    }
    
    public void set(int r, char c, int v) {
        final Cell cell = table[r][c];
        for (Cell number: cell.numbers) {
            number.listeners.removeAll(Collections.singleton(cell));
        }
        cell.numbers.clear();
        setValueWithCascading(cell, v);
    }
    
    public int get(int r, char c) {
        return table[r][c].value;
    }
    
    public int sum(int r, char c, String[] strs) {
        final Cell cell = table[r][c];
        
        int oldValue = cell.value;
        for (Cell number: cell.numbers) {
            number.listeners.removeAll(Collections.singleton(cell));
        }
        cell.numbers.clear();
        
        for (String str: strs) {
            int semicolonIndex = str.indexOf(':');
            if (semicolonIndex < 0) {
                CellIndex cellIndex = parseCellIndex(str);
                Cell number = table[cellIndex.h][cellIndex.w];
                cell.numbers.add(number);
                number.listeners.add(cell);
            }
            else {
                CellIndex ci1 = parseCellIndex(str.substring(0, semicolonIndex));
                CellIndex ci2 = parseCellIndex(str.substring(semicolonIndex + 1));
                
                for (int h = ci1.h; h <= ci2.h; ++h) {
                    for (int w = ci1.w; w <= ci2.w; ++w) {
                        Cell number = table[h][w];
                        cell.numbers.add(number);
                        number.listeners.add(cell);
                    }
                }
            }
        }
        
        cell.value = 0;
        for (Cell number: cell.numbers) {
            cell.value += number.value;
        }
        for (Cell listener: cell.listeners) {
            listener.value -= oldValue;
            listener.value += cell.value;
        }
        
        return cell.value;
    }
    
    private CellIndex parseCellIndex(String str) {
        return new CellIndex(Integer.parseInt(str.substring(1)), str.charAt(0));
    }
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(H, W);
 * obj.set(r,c,v);
 * int param_2 = obj.get(r,c);
 * int param_3 = obj.sum(r,c,strs);
 */

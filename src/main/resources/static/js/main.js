const api = "/api";

// 1. 상품 등록
async function registerProduct() {
    const data = {
        productName: document.getElementById('pName').value,
        productPrice: document.getElementById('pPrice').value,
        productStock: document.getElementById('pStock').value
    };
    await fetch(`${api}/product`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    });
    clearInputs();
    loadProducts();
}

// 2. 상품 전체 조회
async function loadProducts() {
    const res = await fetch(`${api}/products`);
    const products = await res.json();
    const list = document.getElementById('productList');

    list.innerHTML = products.map(p => `
        <div class="item">
            <div class="item-info">
                <b>${p.productName}</b><br>
                <small>${p.productPrice}원 / 재고: ${p.productStock}</small>
            </div>
            <div class="item-btns">
                <button class="btn-order" onclick="orderProduct(${p.id})">주문</button>
                <button class="btn-edit" onclick="setUpdate(${p.id}, '${p.productName}', ${p.productPrice}, ${p.productStock})">수정</button>
                <button class="btn-delete" onclick="deleteProduct(${p.id})">삭제</button>
            </div>
        </div>
    `).join('');
}

// 3. 상품 수정 세팅 (입력창에 값 넣기)
function setUpdate(id, name, price, stock) {
    document.getElementById('productId').value = id;
    document.getElementById('pName').value = name;
    document.getElementById('pPrice').value = price;
    document.getElementById('pStock').value = stock;

    document.getElementById('btnRegister').style.display = 'none';
    document.getElementById('btnUpdate').style.display = 'block';
}

// 4. 상품 수정 실행
async function updateProduct() {
    const id = document.getElementById('productId').value;
    const data = {
        productName: document.getElementById('pName').value,
        productPrice: document.getElementById('pPrice').value,
        productStock: document.getElementById('pStock').value
    };
    await fetch(`${api}/product/${id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    });
    alert("수정되었습니다.");
    resetForm();
    loadProducts();
}

// 5. 상품 삭제
async function deleteProduct(id) {
    if(!confirm("정말 삭제하시겠습니까?")) return;
    await fetch(`${api}/product/${id}`, { method: 'DELETE' });
    loadProducts();
}

// 6. 주문 생성
async function orderProduct(productId) {
    const res = await fetch(`${api}/order`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ productId: productId })
    });
    if(res.ok) {
        alert("주문이 완료되었습니다!");
        loadProducts();
    } else {
        alert("주문에 실패했습니다. (재고 부족 등)");
    }
}

// 7. 주문 단건 조회
async function loadOrder() {
    const id = document.getElementById('orderIdInput').value;
    const res = await fetch(`${api}/order/${id}`);
    if(!res.ok) {
        document.getElementById('orderResult').innerText = "주문 정보를 찾을 수 없습니다.";
        return;
    }
    const data = await res.json();
    document.getElementById('orderResult').innerHTML = `
        <strong>주문 ID:</strong> ${data.orderId}<br>
        <strong>상품명:</strong> ${data.productName}<br>
        <strong>결제 금액:</strong> ${data.productPrice}원
    `;
}

// 입력창 초기화
function clearInputs() {
    document.getElementById('pName').value = '';
    document.getElementById('pPrice').value = '';
    document.getElementById('pStock').value = '';
}

function resetForm() {
    clearInputs();
    document.getElementById('btnRegister').style.display = 'block';
    document.getElementById('btnUpdate').style.display = 'none';
}

// 페이지 로드 시 목록 불러오기
window.onload = loadProducts;
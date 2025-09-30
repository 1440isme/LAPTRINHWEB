<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Cập nhật sản phẩm</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <%@ include file="/common/admin/header.jsp" %>
                <div class="container py-5">
                    <div class="row justify-content-center">
                        <div class="col-md-10">
                            <div class="card p-4">
                                <h4 class="mb-3"><i class="fa fa-box me-2"></i>Cập nhật sản phẩm</h4>

                                <form action="${pageContext.request.contextPath}/admin/product/update" method="post"
                                    enctype="multipart/form-data">
                                    <input type="hidden" name="productId" value="${product.productId}" />
                                    <div class="row g-3">
                                        <div class="col-md-6">
                                            <label class="form-label">Tên sản phẩm</label>
                                            <input type="text" name="productName" class="form-control"
                                                value="${product.productName}" required />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Mã sản phẩm</label>
                                            <input type="text" name="productCode" class="form-control"
                                                value="${product.productCode}" required />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Danh mục</label>
                                            <select name="categoryId" class="form-select">
                                                <c:forEach items="${categories}" var="c">
                                                    <option value="${c.categoryId}" ${product.categoryId==c.categoryId
                                                        ? 'selected' : '' }>${c.categoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Giá</label>
                                            <input type="number" step="0.01" name="price" class="form-control"
                                                value="${product.price}" />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Số lượng</label>
                                            <input type="number" name="amount" class="form-control"
                                                value="${product.amount}" />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Số lượng kho</label>
                                            <input type="number" name="stock" class="form-control"
                                                value="${product.stock}" />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Trạng thái</label>
                                            <select name="status" class="form-select">
                                                <option value="1" ${product.status==1 ? 'selected' : '' }>Active
                                                </option>
                                                <option value="0" ${product.status !=1 ? 'selected' : '' }>Inactive
                                                </option>
                                            </select>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="form-label">Mô tả</label>
                                            <textarea name="description" class="form-control"
                                                rows="3">${product.description}</textarea>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="form-label">Ảnh</label>
                                            <input type="file" name="image" class="form-control" accept="image/*" />
                                            <c:if test="${not empty product.images}">
                                                <small class="text-muted">Ảnh hiện tại: ${product.images}</small>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="d-flex gap-2 mt-3">
                                        <button class="btn btn-primary" type="submit">Lưu</button>
                                        <a class="btn btn-secondary"
                                            href="${pageContext.request.contextPath}/admin/products">Huỷ</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <%@ include file="/common/admin/footer.jsp" %>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>
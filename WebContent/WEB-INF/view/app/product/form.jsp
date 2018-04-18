<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Product</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-10">
							<form role="form" action="/app?cmd=19" method="post">
								<input type="hidden" id="id" name="id">
								<div class="form-group">
									<label for="name">Name</label> <input class="form-control"
										id="name" name="name" placeholder="Enter text">
								</div>
								<div class="form-group">
									<label for="unit">Unit of Measurement</label> <select
										class="form-control" id="unit" name="unit">
										<option>Select</option>
										<option>Kg</option>
										<option>Lt</option>
										<option>Und.</option>
										<option>Pct.</option>
										<option>Cx.</option>
									</select>
								</div>
								<div class="form-group">
									<label for="minquantity">Min. Quantity</label> <input
										type="number" class="form-control" id="minquantity"
										name="minquantity" min="1">
								</div>
								<div class="form-group">
									<label for="price">Price</label>
									<div class="input-group">
										<div class="input-group-addon">$</div>
										<input type="text" data-thousands="." data-decimal=","
											class="form-control money" id="price" name="price"
											placeholder="Enter price">
									</div>
								</div>
								<div class="form-group">
									<div class="checkbox">
										<label> <input type="checkbox" id="ownmanufacturing"
											name="ownmanufacturing"> Own manufacturing
										</label>
									</div>
								</div>
								<div class="form-group">
									<label for="description">Description</label>
									<textarea class="form-control" id="description"
										placeholder="Enter description" cols="10" rows="5"></textarea>
								</div>
								<div class="form-group">
									<label for="references">References</label> <input type="text"
										class="form-control" id="references" name="references"
										data-role="tagsinput" />
								</div>
								<div class="form-group">
									<label for="tags">Tags</label> <input type="text"
										class="form-control" id="tags" name="tags"
										data-role="tagsinput" />
								</div>
								<div class="col-lg-6">
									<button type="submit" class="btn btn-sm btn-danger"
										formaction="/app?cmd=22">Delete</button>
									<button type="submit" class="btn btn-sm btn-primary">Save</button>
									<button type="reset" class="btn btn-sm btn-default"
										onclick="formCancel(${pageContext.request.contextPath}/app/product/list)">Cancel</button>
								</div>
							</form>
						</div>
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</body>
</html>